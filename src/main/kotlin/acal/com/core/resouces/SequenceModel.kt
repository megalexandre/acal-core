package acal.com.core.resouces

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document(collection = "sequence")
class SequenceModel(
    @Id
    val name: String,
    val number: Long,
)
