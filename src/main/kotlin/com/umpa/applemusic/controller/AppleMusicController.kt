package com.umpa.applemusic.controller

import com.umpa.applemusic.clients.AppleMusicHintResponse
import com.umpa.applemusic.clients.AppleMusicResultResponse
import com.umpa.applemusic.domain.AppleMusicService
import org.springframework.http.ResponseEntity
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
        value = ["/songs/{keyword:.+}"]
    )
    fun searchSongsByKeyword(
        @PathVariable keyword: String
    ): ResponseEntity<AppleMusicResultResponse> {
        return ResponseEntity.ok(appleMusicService.searchSongsByKeyword(keyword))
    }

    @GetMapping(
        value = ["/artists/{keyword:.+}"]
    )
    fun searchArtistsByKeyword(
        @PathVariable keyword: String
    ): ResponseEntity<AppleMusicResultResponse> {
        return ResponseEntity.ok(appleMusicService.searchArtistsByKeyword(keyword))
    }

    @GetMapping(
        value = ["/albums/{keyword:.+}"]
    )
    fun searchAlbumsByKeyword(
        @PathVariable keyword: String
    ): ResponseEntity<AppleMusicResultResponse> {
        return ResponseEntity.ok(appleMusicService.searchAlbumsByKeyword(keyword))
    }

    // cursorKey의 경우, 특수문자가 포함돼서 넘어오기 때문에 클라에서 urlEncoding 해서 넘겨야 됨.
    @GetMapping(
        value = ["/next"]
    )
    fun searchNextByCursorKey(
        @RequestParam cursorKey: String
    ): ResponseEntity<AppleMusicResultResponse> {
        return ResponseEntity.ok(appleMusicService.searchNextByCursorKey(cursorKey))
    }

    @GetMapping(
        value = ["/hints"]
    )
    fun searchHintsByKeyword(
        @RequestParam keyword: String
    ): ResponseEntity<AppleMusicHintResponse> {
        return ResponseEntity.ok(appleMusicService.searchHintsByKeyword(keyword))
    }
}
