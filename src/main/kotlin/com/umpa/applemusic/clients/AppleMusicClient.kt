package com.umpa.applemusic.clients

import com.umpa.applemusic.support.enums.AppleMusicType
import com.umpa.applemusic.support.utils.AppleMusicCursorKeyParser
import feign.FeignException
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component

@Component
class AppleMusicClient(
    private val appleMusicApi: AppleMusicApi
) {
    private val logger = LoggerFactory.getLogger(AppleMusicClient::class.java)
    fun getResultsByKeywordAndType(keyword: String, appleMusicType: AppleMusicType): AppleMusicResultResponse {
        logger.info("[getResultsByKeywordAndType] => keyword is $keyword")
        try {
            val results = (
                appleMusicApi.getResultsByName(
                    term = keyword,
                    types = appleMusicType.type
                ).body?.let {
                    it["results"]
                }
                ) as LinkedHashMap<String, LinkedHashMap<String, Any>>
            return getAppleMusicResultsByTypes(results, appleMusicType.type)
        } catch (e: FeignException) {
        }
        throw RuntimeException()
    }

    fun getNextByCursorKey(cursorKey: String): AppleMusicResultResponse {
        logger.info("[getNextByCursorKey] => cursorKey is $cursorKey")
        try {
            val appleMusicCursorKey = AppleMusicCursorKeyParser.parse(cursorKey)
            val results = (
                appleMusicApi.getResultsByName(
                    term = appleMusicCursorKey.term,
                    offset = appleMusicCursorKey.offset.toInt(),
                    types = appleMusicCursorKey.types
                )?.body?.let {
                    it["results"]
                }
                ) as LinkedHashMap<String, LinkedHashMap<String, Any>>
            return getAppleMusicResultsByTypes(results, appleMusicCursorKey.types)
        } catch (e: FeignException) {
        }
        throw RuntimeException()
    }

    fun getHintsByKeyword(term: String): AppleMusicHintResponse {
        logger.info("[getHintsByKeyword] => term is $term")
        try {
            val results = (
                appleMusicApi.getHintsByKeyword(
                    term = term
                )?.body?.let {
                    it["results"]
                }
                ) as LinkedHashMap<String, List<String>>
            return AppleMusicHintResponse(
                hints = results["terms"] ?: emptyList()
            )
        } catch (e: FeignException) {
        }
        throw RuntimeException()
    }

    private fun getAppleMusicResultsByTypes(
        results: LinkedHashMap<String, LinkedHashMap<String, Any>>,
        types: String
    ): AppleMusicResultResponse {
        return results[types]?.let {
            val data = it["data"] as List<Any>
            val next = it["next"] as String?
            return AppleMusicResultResponse(data, next?.substring(22))
        } ?: AppleMusicResultResponse(emptyList(), null)
    }
}
