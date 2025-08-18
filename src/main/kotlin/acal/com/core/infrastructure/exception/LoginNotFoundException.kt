package acal.com.core.infrastructure.exception

class LoginNotFoundException(override val message: String): RuntimeException(message)