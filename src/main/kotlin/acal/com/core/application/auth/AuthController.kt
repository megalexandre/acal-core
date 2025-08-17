package acal.com.core.application.auth

import acal.com.core.application.auth.data.`in`.Login
import acal.com.core.application.auth.data.out.UserDataResponse
import acal.com.core.application.auth.data.out.UserResponse
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController
import kotlin.math.log

@RestController
@RequestMapping(
    value = ["/auth"],
)
class AuthController {

    private val logins: Array<String> = arrayOf(
        "alexandre@acal.com",
        "edvaldo@acal.com",
        "alzenir@acal.com"
    )

    @PostMapping("/login")
    @ResponseStatus(HttpStatus.OK)
    fun login(@RequestBody login: Login): UserResponse {

        if (!logins.contains(login.name)) {
            throw RuntimeException("Invalid login")
        }
        return UserResponse(
            status = "success",
            data = UserDataResponse(
                id = "1",
                email = "alexandre@acal.com.br",
                name = "alexandre",
                userName = "Alexandre",
                firstName = "Alexandre",
                lastName = "Queiroz",
            ),
            token = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9." +
                    "eyJzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6IkFsZXhhbmRyZSBRdWVpcm96IiwiaWF0IjoxNTE2MjM5MDIyfQ." +
                    "TJVA95OrM7E2cBab30RMHrHDcEfxjoYZgeFONFh7HgQ"
        )
    }
}