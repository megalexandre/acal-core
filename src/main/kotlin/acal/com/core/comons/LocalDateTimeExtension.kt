package acal.com.core.comons

import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*

fun LocalDateTime.current(): String =
    this.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss"))

fun LocalDateTime.currentDate(): String =
    this.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"))

fun LocalDateTime.currentTime(): String =
    this.format(DateTimeFormatter.ofPattern("HH:mm:ss"))
