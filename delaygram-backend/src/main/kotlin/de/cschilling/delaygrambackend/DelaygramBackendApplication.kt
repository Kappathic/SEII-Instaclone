package de.cschilling.delaygrambackend

import de.cschilling.delaygrambackend.model.Post
import de.cschilling.delaygrambackend.model.User
import de.cschilling.delaygrambackend.repository.PostRepository
import de.cschilling.delaygrambackend.repository.UserRepository
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class DelaygramBackendApplication(
    val userRepository: UserRepository,
    val postRepository: PostRepository
) : CommandLineRunner {
    override fun run(vararg args: String?) {
        val user1 = User(
            "admin",
            "\$2y\$10\$mt1Ev5vlAx2/RZrlFicF1uQNJk3SCGiCYLn.exBGEHL09hwWJfUNi",
            "admin",
            "admin@example.com",
            null,
            mutableListOf(),
            mutableSetOf()
        )
        val post1 = Post("Das ist der erste Post",null, setOf("test1","test2"))
        postRepository.save(post1)
        user1.posts.add(post1)
        userRepository.save(user1)
        userRepository.save(
            User(
                "user",
                "\$2y\$10\$mt1Ev5vlAx2/RZrlFicF1uQNJk3SCGiCYLn.exBGEHL09hwWJfUNi",
                "user",
                "user@example.com",
                null,
                mutableListOf(),
                mutableSetOf()
            )
        )
    }

}

fun main(args: Array<String>) {
    runApplication<DelaygramBackendApplication>(*args)
}
