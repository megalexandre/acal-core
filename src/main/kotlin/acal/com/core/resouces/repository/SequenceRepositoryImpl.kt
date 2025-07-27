package acal.com.core.resouces.repository

import acal.com.core.resouces.SequenceModel
import org.springframework.data.mongodb.core.FindAndModifyOptions
import org.springframework.data.mongodb.core.MongoOperations
import org.springframework.data.mongodb.core.query.Criteria
import org.springframework.data.mongodb.core.query.Query
import org.springframework.data.mongodb.core.query.Update
import org.springframework.stereotype.Repository

@Repository
class SequenceRepositoryImpl(
    private val mongoOperations: MongoOperations
) {
    fun generateSequence(seqName: String): Long =
        mongoOperations.findAndModify(
            Query(Criteria.where("_id").`is`(seqName)),
            Update().inc("number", 1),
            FindAndModifyOptions.options().returnNew(true).upsert(true),
            SequenceModel::class.java
        )?.number ?: 1

}