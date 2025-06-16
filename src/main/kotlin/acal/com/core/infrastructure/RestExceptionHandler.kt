package acal.com.core.infrastructure

import com.fasterxml.jackson.databind.exc.MismatchedInputException
import org.springframework.http.HttpStatus.BAD_REQUEST
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

}
