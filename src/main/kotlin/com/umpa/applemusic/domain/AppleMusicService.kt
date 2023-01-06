package com.umpa.applemusic.domain

import com.umpa.applemusic.clients.AppleMusicClient
import org.springframework.stereotype.Service

@Service
class AppleMusicService(
    private val appleMusicClient: AppleMusicClient
) {
    fun searchSongsByKeyword(keyword: String) {
        appleMusicClient.getResultsByKeyword(keyword)
    }

    fun searchArtistsByKeyword(keyword: String) {
        // TODO
    }

    fun searchAlbumsByKeyword(keyword: String) {
        // TODO
    }

    fun searchNextByCursorKey(cursorKey: String) {
        // TODO
    }

    fun searchHintsByKeyword(keyword: String) {
        // TODO
    }
}