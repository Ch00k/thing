package my.thingservice.thing

import org.springframework.data.repository.CrudRepository

interface ThingRepository : CrudRepository<Thing, Long> {
    fun findByName(name: String): Thing?

    fun findAllByOrderByCreatedAtDesc(): Iterable<Thing>
}
