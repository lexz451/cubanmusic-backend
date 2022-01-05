package info.cubanmusic.cubanmusicapi.repository;

import info.cubanmusic.cubanmusicapi.model.Organization
import org.springframework.cache.annotation.CacheEvict
import org.springframework.cache.annotation.Cacheable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.JpaSpecificationExecutor
import org.springframework.data.jpa.repository.Query
import java.util.*

interface OrganizationRepository : JpaRepository<Organization, UUID>, JpaSpecificationExecutor<Organization> {

    @Cacheable("organizations")
    override fun findAll(): MutableList<Organization>

    @CacheEvict(
        cacheNames = [
            "organizations"
        ],
        allEntries = true
    )
    override fun <S : Organization?> save(entity: S): S
}