package acal.com.core.infrastructure.serializer

import com.fasterxml.jackson.core.JsonGenerator
import com.fasterxml.jackson.core.JsonParser
import com.fasterxml.jackson.databind.DeserializationContext
import com.fasterxml.jackson.databind.JsonDeserializer
import com.fasterxml.jackson.databind.JsonSerializer
import com.fasterxml.jackson.databind.SerializerProvider
import java.time.LocalDate
import java.time.OffsetDateTime
import java.time.format.DateTimeFormatter

class LocalDateSerializer : JsonSerializer<LocalDate>() {
    override fun serialize(value: LocalDate, gen: JsonGenerator, serializers: SerializerProvider) {
        gen.writeString(value.format(DateTimeFormatter.ISO_LOCAL_DATE))
    }
}

class LocalDateDeserializer : JsonDeserializer<LocalDate>() {
    override fun deserialize(p: JsonParser, ctxt: DeserializationContext): LocalDate =
        runCatching {
            LocalDate.parse(p.text, DateTimeFormatter.ISO_LOCAL_DATE)
        }.runCatching {
            val dateText = p.text.split("T").firstOrNull() ?: p.text
            LocalDate.parse(dateText, DateTimeFormatter.ISO_LOCAL_DATE)
        }.recoverCatching {
            val odt = OffsetDateTime.parse(p.text)
            odt.toLocalDate()
        }.getOrThrow()
}

