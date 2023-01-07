package com.umpa.applemusic.clients

import feign.RequestInterceptor
import feign.RequestTemplate
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import org.bouncycastle.asn1.pkcs.PrivateKeyInfo
import org.bouncycastle.openssl.PEMParser
import org.bouncycastle.openssl.jcajce.JcaPEMKeyConverter
import org.springframework.beans.factory.annotation.Value
import org.springframework.cloud.openfeign.EnableFeignClients
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.core.io.ClassPathResource
import java.io.Reader
import java.io.StringReader
import java.nio.file.Files
import java.nio.file.Paths
import java.time.Duration
import java.util.Date

@Configuration
@EnableFeignClients
internal class AppleMusicConfig {
    @Bean
    fun authorizationInterceptor(): RequestInterceptor {
        return AppleMusicAuthorizationInterceptor()
    }

    class AppleMusicAuthorizationInterceptor : RequestInterceptor {
        @Value("\${clients.apple-music.issuer}")
        lateinit var issuer: String
        @Value("\${clients.apple-music.api-key}")
        lateinit var apiKey: String
        @Value("\${clients.apple-music.alg}")
        lateinit var alg: String

        override fun apply(template: RequestTemplate) {
            val resource = ClassPathResource("AppleMusic_AuthKey.p8")
            val privateKey = String(Files.readAllBytes(Paths.get(resource.uri)))
            val pemReader: Reader = StringReader(privateKey)
            val pemParser = PEMParser(pemReader)
            val converter = JcaPEMKeyConverter()
            val pKey = converter.getPrivateKey(pemParser.readObject() as PrivateKeyInfo)
            val token = Jwts.builder()
                .setHeaderParam("alg", alg)
                .setHeaderParam("kid", apiKey)
                .setIssuer(issuer)
                .setExpiration(Date(Date().time + Duration.ofDays(180L).toMillis()))
                .signWith(pKey, SignatureAlgorithm.ES256)
                .compact()
            template.header("Authorization", "Bearer $token")
        }
    }
}
