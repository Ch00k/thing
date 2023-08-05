package my.thingservice.thing

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager
import org.springframework.data.repository.findByIdOrNull

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
class RepositoriesTests
@Autowired
constructor(
    val entityManager: TestEntityManager,
    val thingRepository: ThingRepository,
) {

    @Test
    fun `When findByIdOrNull then return Thing`() {
        val fooThing = Thing("foo")
        entityManager.persist(fooThing)
        entityManager.flush()
        val found = thingRepository.findByIdOrNull(fooThing.id!!)
        Assertions.assertEquals(found, fooThing)
    }

    @Test
    fun `When findByIdOrNull then return null`() {
        val found = thingRepository.findByIdOrNull(42)
        Assertions.assertNull(found)
    }

    @Test
    fun `When findByName then return Thing`() {
        val barThing = Thing("bar")
        entityManager.persist(barThing)
        entityManager.flush()
        val found = thingRepository.findByName("bar")
        Assertions.assertEquals(found, barThing)
    }
}
