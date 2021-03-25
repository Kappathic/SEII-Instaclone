package de.cschilling.delaygrambackend

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class DelaygramBackendApplication

fun main(args: Array<String>) {
	runApplication<DelaygramBackendApplication>(*args)
}
