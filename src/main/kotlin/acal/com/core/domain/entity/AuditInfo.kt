package acal.com.core.domain.entity

import java.time.LocalDateTime

class AuditInfo (
    val createdAt: LocalDateTime,
    val updatedAt: LocalDateTime,
)