package com.umpa.applemusic.support.error

data class ApplicationException(
    val errorType: ErrorType
) : RuntimeException(errorType.message)
