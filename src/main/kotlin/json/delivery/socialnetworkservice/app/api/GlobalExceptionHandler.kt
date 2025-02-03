package json.delivery.socialnetworkservice.app.api

import json.delivery.socialnetworkservice.app.exception.ArticleNotFoundException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice
import org.springframework.web.bind.support.WebExchangeBindException

@RestControllerAdvice
class GlobalExceptionHandler {

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

    @ExceptionHandler(IllegalArgumentException::class)
    fun handleValidationExceptions(ex: IllegalArgumentException): ResponseEntity<String> {
        return ResponseEntity(ex.message, HttpStatus.BAD_REQUEST)
    }

    @ExceptionHandler(Exception::class)
    fun handleRunTimeException(ex: Exception): ResponseEntity<String> {
        return ResponseEntity("An unexpected error occurred: ${ex.message}", HttpStatus.INTERNAL_SERVER_ERROR)
    }
}
