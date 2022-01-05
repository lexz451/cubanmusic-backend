package info.cubanmusic.cubanmusicapi.repository;

import info.cubanmusic.cubanmusicapi.model.Venue
import org.springframework.cache.annotation.CacheEvict
import org.springframework.cache.annotation.Cacheable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.JpaSpecificationExecutor
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface VenueRepository : JpaRepository<Venue, UUID>, JpaSpecificationExecutor<Venue> {

    @Cacheable("venues")
    override fun findAll(): MutableList<Venue>

    @CacheEvict(
        cacheNames = [
            "venues"
        ],
        allEntries = true
    )
    override fun <S : Venue?> save(entity: S): S
}