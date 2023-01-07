package com.umpa.applemusic.support.error

import org.springframework.http.HttpStatus

data class FeignClientException(
    override val message: String? = DefaultErrorTypeType.INTERNAL_SERVER_ERROR.message,
    val statusCode: HttpStatus
) : RuntimeException(message)
