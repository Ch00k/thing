package my.thingservice.thing

import org.springframework.data.repository.findByIdOrNull
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.server.ResponseStatusException

@RestController
@RequestMapping("/api/thing")
class ArticleController(private val repository: ThingRepository) {

    @GetMapping("/") fun findAll() = repository.findAllByOrderByCreatedAtDesc()

    @GetMapping("/{id}")
    fun findOne(@PathVariable id: Long) =
        repository.findByIdOrNull(id) ?: throw ResponseStatusException(HttpStatus.NOT_FOUND)
}
