package com.umpa.applemusic.support.error

import org.springframework.http.HttpStatus

enum class DefaultErrorTypeType(
    override val message: String,
    override val statusCode: HttpStatus
) : ErrorType {
    INTERNAL_SERVER_ERROR("일시적으로 에러가 발생했습니다.", HttpStatus.INTERNAL_SERVER_ERROR)
}
