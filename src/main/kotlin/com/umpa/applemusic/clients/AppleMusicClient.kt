package com.umpa.applemusic.clients

import org.springframework.stereotype.Component

@Component
class AppleMusicClient(
    private val appleMusicApi: AppleMusicApi
) {
    fun getResultsByKeyword(keyword: String) {
        val result = appleMusicApi.getResultsByName(
            term = keyword
        )
    }

}