package com.umpa.applemusic.support.utils

import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test

internal class AppleMusicCursorKeyParserTest {
    @Test
    fun `cursorKey parser 테스트`() {
        val cursorKey = "offset=20&term=zico&types=songs"
        val appleMusicCursorKey = AppleMusicCursorKeyParser.parse(cursorKey)
        Assertions.assertThat(appleMusicCursorKey.offset).isEqualTo("20")
        Assertions.assertThat(appleMusicCursorKey.term).isEqualTo("zico")
        Assertions.assertThat(appleMusicCursorKey.types).isEqualTo("songs")
    }
}
