package acal.com.core.infrastructure

import acal.com.core.infrastructure.exception.DataNotFoundException
import com.fasterxml.jackson.databind.exc.MismatchedInputException
import org.springframework.dao.DuplicateKeyException
import org.springframework.http.HttpStatus.BAD_REQUEST
import org.springframework.http.HttpStatus.NO_CONTENT
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestControllerAdvice
import org.springframework.web.context.request.WebRequest
import java.time.LocalDateTime

@RestControllerAdvice
class RestExceptionHandler {

    data class ErrorResponse(
        val timestamp: LocalDateTime = LocalDateTime.now(),
        val status: Int,
        val error: String,
        val message: String?,
        val path: String?
    )

    @ExceptionHandler(MismatchedInputException::class)
    @ResponseStatus(BAD_REQUEST)
    fun handleMismatchedInputException(
        ex: MismatchedInputException,
        request: WebRequest
    ): ResponseEntity<ErrorResponse> =ResponseEntity(
            ErrorResponse(
                status = BAD_REQUEST.value(),
                error = BAD_REQUEST.reasonPhrase,
                message = "Field '${ex.path.lastOrNull()?.fieldName ?: "Unknown field"}' is required.",
                path = request.getDescription(false).removePrefix("uri=")
            ), BAD_REQUEST
    )

    @ExceptionHandler(DuplicateKeyException::class)
    fun handleDuplicateKeyException(ex: DuplicateKeyException): ResponseEntity<Any> {
        val errorMessage = "Registro duplicado. ${ex.message}"
        val errorResponse = mapOf(
            "status" to BAD_REQUEST.value(),
            "error" to "Duplicate Key",
            "message" to errorMessage
        )
        return ResponseEntity(errorResponse, BAD_REQUEST)
    }

    @ExceptionHandler(DataNotFoundException::class)
    fun handleNotFoundException(ex: DataNotFoundException): ResponseEntity<Any> {
        val errorResponse = mapOf(
            "status" to NO_CONTENT.value(),
            "message" to "resources not found",
        )
        return ResponseEntity(errorResponse, NO_CONTENT)
    }


}
