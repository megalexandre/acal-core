package acal.com.core.resouces.repository

import java.time.LocalDateTime

abstract class DefaultModel {
    open var updatedAt: LocalDateTime = LocalDateTime.now()
    open var deletedAt: LocalDateTime? = null
}