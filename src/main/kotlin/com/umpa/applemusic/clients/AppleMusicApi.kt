package com.umpa.applemusic.clients

import org.springframework.cloud.openfeign.FeignClient
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam

@FeignClient(
    value = "apple-music-api",
    url = "\${clients.apple-music.url}"
)
interface AppleMusicApi {
    @GetMapping(
        value = ["/v1/catalog/kr/search"],
        consumes = [MediaType.APPLICATION_JSON_VALUE]
    )
    fun getResultsByName(
        @RequestParam term: String,
        @RequestParam limit: Int = LIMIT,
        @RequestParam types: String,
        @RequestParam offset: Int = 0
    ): ResponseEntity<LinkedHashMap<String, LinkedHashMap<String, Any>>>

    @GetMapping(
        value = ["/v1/catalog/kr/search/hints"],
        consumes = [MediaType.APPLICATION_JSON_VALUE]
    )
    fun getHintsByKeyword(
        @RequestParam term: String,
        @RequestParam limit: Int = HINT_LIMIT
    ): ResponseEntity<LinkedHashMap<String, LinkedHashMap<String, Any>>>

    companion object {
        const val LIMIT = 20
        const val HINT_LIMIT = 10
    }
}
