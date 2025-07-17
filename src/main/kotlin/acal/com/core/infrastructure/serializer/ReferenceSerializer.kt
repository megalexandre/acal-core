package acal.com.core.infrastructure.serializer

import acal.com.core.domain.entity.Reference
import com.fasterxml.jackson.core.JsonGenerator
import com.fasterxml.jackson.core.JsonParser
import com.fasterxml.jackson.databind.DeserializationContext
import com.fasterxml.jackson.databind.JsonDeserializer
import com.fasterxml.jackson.databind.JsonSerializer
import com.fasterxml.jackson.databind.SerializerProvider
import org.springframework.core.convert.converter.Converter
import org.springframework.stereotype.Component


class ReferenceSerializer : JsonSerializer<Reference>() {

    override fun serialize(value: Reference, gen: JsonGenerator, serializers: SerializerProvider) {
        gen.writeString(value.toString())
    }
}

class ReferenceDeserializer : JsonDeserializer<Reference>() {

    override fun deserialize(p: JsonParser, ctxt: DeserializationContext): Reference =
        Reference.of(p.text)
}

@Component
class StringToReferenceConverter : Converter<String, Reference> {
    override fun convert(source: String): Reference =
        Reference.of(source)
}