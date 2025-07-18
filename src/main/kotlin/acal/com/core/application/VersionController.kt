package acal.com.core.application

import org.springframework.beans.factory.annotation.Value
import org.springframework.http.ResponseEntity
import org.springframework.http.ResponseEntity.ok
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import java.time.Instant
import java.time.ZoneId
import java.time.format.DateTimeFormatter

@RestController
class VersionController(
    @Value("\${spring.application.name:core}") private val applicationName: String,
    @Value("\${acal.version:development}") private val version: String
) {

    private val startedAt: String = Instant.now()
        .atZone(ZoneId.systemDefault())
        .format(DateTimeFormatter.ISO_LOCAL_DATE_TIME)

    @GetMapping("/version")
    fun getVersion(): ResponseEntity<Map<String, Any>> {
        val versionInfo = mapOf(
            "application" to applicationName,
            "version" to version,
            "startedAt" to startedAt,
        )

        return ok(versionInfo)
    }

}
