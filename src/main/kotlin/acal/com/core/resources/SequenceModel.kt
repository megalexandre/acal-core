package acal.com.core.resources

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document(collection = "sequence")
class SequenceModel(
    @Id
    val name: String,
    val number: Long,
)
