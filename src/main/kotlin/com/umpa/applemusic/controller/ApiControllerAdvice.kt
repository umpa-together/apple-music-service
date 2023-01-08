package com.umpa.applemusic.controller

import com.umpa.applemusic.support.error.ApplicationException
import com.umpa.applemusic.support.error.DefaultErrorType
import com.umpa.applemusic.support.error.ErrorResponse
import com.umpa.applemusic.support.error.FeignClientException
import org.slf4j.LoggerFactory
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class ApiControllerAdvice {
    private val logger = LoggerFactory.getLogger(ApiControllerAdvice::class.java)

    @ExceptionHandler(ApplicationException::class)
    fun handleApplicationException(e: ApplicationException): ResponseEntity<ErrorResponse> {
        logger.info("ApplicationException: {}", e.message, e)
        return ResponseEntity.status(e.errorType.statusCode.value())
            .body(ErrorResponse(e.errorType.message, e.errorType.statusCode.value()))
    }

    @ExceptionHandler(FeignClientException::class)
    fun handleFeignException(e: FeignClientException): ResponseEntity<ErrorResponse> {
        logger.info("FeignException: {}", e.message, e)
        val errorType = DefaultErrorType.INTERNAL_SERVER_ERROR
        return ResponseEntity.status(errorType.statusCode.value())
            .body(ErrorResponse(errorType.message, errorType.statusCode.value()))
    }

    @ExceptionHandler(RuntimeException::class)
    fun handleRuntimeException(e: RuntimeException): ResponseEntity<ErrorResponse> {
        logger.info("RuntimeException: {}", e.message, e)
        val errorType = DefaultErrorType.INTERNAL_SERVER_ERROR
        return ResponseEntity.status(errorType.statusCode.value())
            .body(ErrorResponse(errorType.message, errorType.statusCode.value()))
    }
}
