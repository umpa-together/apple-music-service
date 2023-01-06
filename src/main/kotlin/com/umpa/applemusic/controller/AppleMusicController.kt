package com.umpa.applemusic.controller

import com.umpa.applemusic.domain.AppleMusicService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/apple-music")
class AppleMusicController(
    private val appleMusicService: AppleMusicService
) {

    @GetMapping(
        value = ["/songs/{keyword}"]
    )
    fun searchSongsByKeyword(
        @PathVariable keyword: String
    ) {
        // TODO
    }

    @GetMapping(
        value = ["/artists/{keyword}"]
    )
    fun searchArtistsByKeyword(
        @PathVariable keyword: String
    ) {
        // TODO
    }

    @GetMapping(
        value = ["/albums/{keyword}"]
    )
    fun searchAlbumsByKeyword(
        @PathVariable keyword: String
    ) {
        // TODO
    }

    @GetMapping(
        value = ["/next"]
    )
    fun searchNextByCursorKey(
        @RequestParam cursorKey: String
    ) {
        // TODO
    }

    @GetMapping(
        value = ["/hints"]
    )
    fun searchHintsByKeyword(
        @RequestParam keyword: String
    ) {
        // TODO
    }
}