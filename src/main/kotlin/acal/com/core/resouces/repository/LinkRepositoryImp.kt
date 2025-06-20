package acal.com.core.resouces.repository

import acal.com.core.domain.datasource.LinkDataSource
import acal.com.core.domain.entity.Link
import acal.com.core.resouces.LinkModel
import acal.com.core.resouces.toDomain
import acal.com.core.resouces.toEntity
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Repository

@Repository
class LinkRepositoryImp(
    val linkRepository: LinkRepository
): LinkDataSource {

    override fun save(link: Link): Link =
        linkRepository.save(link.toEntity()).toDomain()

    override fun findById(id: String): Link? =
        linkRepository.findById(id).orElse(null)?.toDomain()

}

interface LinkRepository: MongoRepository<LinkModel, String>

