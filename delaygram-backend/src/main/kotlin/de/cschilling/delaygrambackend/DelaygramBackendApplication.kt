package de.cschilling.delaygrambackend

import de.cschilling.delaygrambackend.model.User
import de.cschilling.delaygrambackend.repository.UserRepository
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class DelaygramBackendApplication(
	val userRepository: UserRepository
) : CommandLineRunner{
	override fun run(vararg args: String?) {
		userRepository.save(User(1L, "admin", "\$2y\$10\$mt1Ev5vlAx2/RZrlFicF1uQNJk3SCGiCYLn.exBGEHL09hwWJfUNi", "admin", "admin@example.com",null,
			mutableSetOf()))
		userRepository.save(User(2L, "user", "\$2y\$10\$mt1Ev5vlAx2/RZrlFicF1uQNJk3SCGiCYLn.exBGEHL09hwWJfUNi", "user", "user@example.com",null,
			mutableSetOf()))
	}

}

fun main(args: Array<String>) {
	runApplication<DelaygramBackendApplication>(*args)
}
