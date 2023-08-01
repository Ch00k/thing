package my.thingservice.thing

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication class ThingApplication

fun main(args: Array<String>) {
    runApplication<ThingApplication>(*args)
}
