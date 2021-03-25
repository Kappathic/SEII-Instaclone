package controller.cschilling.delaygrambackend

import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.ComponentScan

@SpringBootApplication
class DelaygramBackendApplication

fun main(args: Array<String>) {
	runApplication<DelaygramBackendApplication>(*args)
}
