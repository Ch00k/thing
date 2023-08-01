package my.thingservice.thing

import com.ninjasquad.springmockk.MockkBean
import io.mockk.every
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.data.repository.findByIdOrNull
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.*

@WebMvcTest
class HttpControllersTests(@Autowired val mockMvc: MockMvc) {
    @MockkBean lateinit var thingRepository: ThingRepository

    @Test
    fun `List things`() {
        val fooThing = Thing("foo")
        val barThing = Thing("bar")
        every { thingRepository.findAllByOrderByCreatedAtDesc() } returns listOf(fooThing, barThing)
        mockMvc
            .perform(get("/api/thing/").accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk)
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("\$.[0].id").value(fooThing.id))
            .andExpect(jsonPath("\$.[0].name").value(fooThing.name))
            .andExpect(jsonPath("\$.[1].id").value(barThing.id))
            .andExpect(jsonPath("\$.[1].name").value(barThing.name))
    }

    @Test
    fun `Get non-existent thing by ID`() {
        every { thingRepository.findByIdOrNull(42) } returns null
        mockMvc
            .perform(get("/api/thing/42").accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNotFound)
    }

    @Test
    fun `Get existing thing by ID`() {
        val fooThing = Thing("foo", id = 42)
        every { thingRepository.findByIdOrNull(fooThing.id!!) } returns fooThing
        mockMvc
            .perform(get("/api/thing/${fooThing.id}").accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk)
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("\$.id").value(fooThing.id))
            .andExpect(jsonPath("\$.name").value(fooThing.name))
    }
}
