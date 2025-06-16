package acal.com.core.infrastructure.serializer

import com.fasterxml.jackson.core.JsonGenerator
import com.fasterxml.jackson.core.JsonParser
import com.fasterxml.jackson.databind.DeserializationContext
import com.fasterxml.jackson.databind.JsonDeserializer
import com.fasterxml.jackson.databind.JsonSerializer
import com.fasterxml.jackson.databind.SerializerProvider
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class LocalDateTimeSerializer : JsonSerializer<LocalDateTime>() {

    override fun serialize(value: LocalDateTime, gen: JsonGenerator, serializers: SerializerProvider) {
        gen.writeString(value.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME))
    }

}

class LocalDateTimeDeserializer : JsonDeserializer<LocalDateTime>() {
    override fun deserialize(p: JsonParser, ctxt: DeserializationContext): LocalDateTime =
        LocalDateTime.parse(p.text, DateTimeFormatter.ISO_LOCAL_DATE_TIME)

}
