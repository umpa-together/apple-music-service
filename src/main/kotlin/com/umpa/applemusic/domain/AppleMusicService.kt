package com.umpa.applemusic.domain

import com.umpa.applemusic.clients.AppleMusicClient
import com.umpa.applemusic.clients.AppleMusicHintResponse
import com.umpa.applemusic.clients.AppleMusicResultResponse
import com.umpa.applemusic.support.enums.AppleMusicType
import org.springframework.stereotype.Service

@Service
class AppleMusicService(
    private val appleMusicClient: AppleMusicClient
) {
    fun searchSongsByKeyword(keyword: String): AppleMusicResultResponse {
        // TODO keyword -> RecentKeyword 등록
        return appleMusicClient.getResultsByKeywordAndType(keyword, AppleMusicType.SONGS)
    }

    fun searchArtistsByKeyword(keyword: String): AppleMusicResultResponse {
        return appleMusicClient.getResultsByKeywordAndType(keyword, AppleMusicType.ARTISTS)
    }

    fun searchAlbumsByKeyword(keyword: String): AppleMusicResultResponse {
        return appleMusicClient.getResultsByKeywordAndType(keyword, AppleMusicType.ALBUMS)
    }

    fun searchNextByCursorKey(cursorKey: String): AppleMusicResultResponse {
        return appleMusicClient.getNextByCursorKey(cursorKey)
    }

    fun searchHintsByKeyword(keyword: String): AppleMusicHintResponse {
        return appleMusicClient.getHintsByKeyword(keyword)
    }
}