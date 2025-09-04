package acal.com.core.application.auth.data.out

data class UserResponse (
    val status: String,
    val token: String,
    val data: UserDataResponse,
)

data class UserDataResponse(
    val id: String,
    val email: String,
    val name: String,
    val userName: String,
    val firstName: String,
    val lastName: String,
)

