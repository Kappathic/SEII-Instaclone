package de.cschilling.delaygrambackend

import de.cschilling.delaygrambackend.model.Post
import de.cschilling.delaygrambackend.model.User
import de.cschilling.delaygrambackend.repository.PostRepository
import de.cschilling.delaygrambackend.repository.UserRepository
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import java.util.*

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
            "Das ist das Profil vom Admin",
            "admin@example.com",
            null,
            mutableListOf(),
            0,
            mutableSetOf()
        )
        val post1 = Post("Das ist der erste Admin Post",
            null,Date(),
            mutableListOf(),
            mutableSetOf(),
            setOf("test1","test2"))
        postRepository.save(post1)
        user1.posts.add(post1)
        userRepository.save(user1)
        userRepository.save(
            User(
                "user",
                "\$2y\$10\$mt1Ev5vlAx2/RZrlFicF1uQNJk3SCGiCYLn.exBGEHL09hwWJfUNi",
                "user",
                "Das ist das Profil vom User",
                "user@example.com",
                null,
                mutableListOf(postRepository.save(Post("Das ist der erste User Post",
                    null,Date(),
                    mutableListOf(),
                    mutableSetOf(),
                setOf("test1","test2"))),
                postRepository.save(Post("Das ist der zweite User Post",
                    null,Date(),
                    mutableListOf(),
                    mutableSetOf(),
                setOf("test1","test2"))),
                postRepository.save(Post("Das ist der dritte User Post",
                    null,Date(),
                    mutableListOf(),
                    mutableSetOf(),
                setOf("test1","test2"))),

                    ),
                0,
                mutableSetOf()
            )
        )

    }

}

fun main(args: Array<String>) {
    runApplication<DelaygramBackendApplication>(*args)
}
