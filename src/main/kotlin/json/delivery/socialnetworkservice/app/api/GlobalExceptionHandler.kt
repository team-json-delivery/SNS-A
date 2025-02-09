package json.delivery.socialnetworkservice.app.api

import io.github.oshai.kotlinlogging.KotlinLogging
import json.delivery.socialnetworkservice.app.exception.ArticleNotFoundException
import org.springframework.beans.TypeMismatchException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice
import org.springframework.web.bind.support.WebExchangeBindException
import org.springframework.web.server.ServerWebInputException

@RestControllerAdvice
class GlobalExceptionHandler {
    private val logger = KotlinLogging.logger {}

    @ExceptionHandler(ArticleNotFoundException::class)
    fun handleArticleNotFoundException(ex: ArticleNotFoundException): ResponseEntity<String> {
        return ResponseEntity(ex.message, HttpStatus.NOT_FOUND)
    }

    @ExceptionHandler(WebExchangeBindException::class)
    fun handleWebExchangeBindException(ex: WebExchangeBindException): ResponseEntity<String> {
        return ResponseEntity(
            ex.bindingResult.fieldErrors.map { it.defaultMessage }.joinToString("|"),
            HttpStatus.BAD_REQUEST,
        )
    }

    @ExceptionHandler(IllegalArgumentException::class, ServerWebInputException::class)
    fun handleValidationExceptions(ex: Throwable): ResponseEntity<String> {
        return ResponseEntity(ex.message, HttpStatus.BAD_REQUEST)
    }

    @ExceptionHandler(Exception::class)
    fun handleGlobalException(ex: Exception): ResponseEntity<String> {
        logger.error(ex) { ex.message }
        return ResponseEntity("An unexpected error occurred: ${ex.message}", HttpStatus.INTERNAL_SERVER_ERROR)
    }
}
