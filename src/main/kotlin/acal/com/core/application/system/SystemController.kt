package acal.com.core.application.system

import acal.com.core.domain.usecase.system.BackupCreateUseCase
import org.springframework.core.io.ByteArrayResource
import org.springframework.core.io.Resource
import org.springframework.http.HttpHeaders
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

@RestController
@RequestMapping(
    value = ["/system"],
)
class SystemController(
    private val backupCreateUseCase: BackupCreateUseCase
) {

    @GetMapping("/backup")
    fun backup(): ResponseEntity<Resource> {
        val backupBytes = backupCreateUseCase.execute()
        val timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd_HH-mm-ss"))
        val filename = "backup_$timestamp.zip"

        val resource = ByteArrayResource(backupBytes)

        return ResponseEntity.ok()
            .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"$filename\"")
            .contentType(MediaType.APPLICATION_OCTET_STREAM)
            .contentLength(resource.contentLength())
            .body(resource)
    }

}
