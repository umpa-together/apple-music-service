package com.umpa.applemusic.support.utils

import com.umpa.applemusic.clients.AppleMusicCursorKey

class AppleMusicCursorKeyParser {
    companion object {
        fun parse(cursorKey: String): AppleMusicCursorKey {
            val (offset, term, types) = cursorKey.split("&").map {
                it.split("=")[1]
            }
            return AppleMusicCursorKey(offset, term, types)
        }
    }
}