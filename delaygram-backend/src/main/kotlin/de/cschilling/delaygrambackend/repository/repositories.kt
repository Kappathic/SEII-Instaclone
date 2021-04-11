package de.cschilling.delaygrambackend.repository

import de.cschilling.delaygrambackend.model.Post
import de.cschilling.delaygrambackend.model.User
import org.springframework.data.jpa.repository.JpaRepository

interface UserRepository : JpaRepository<User, Long> {
    fun findByUsername(name: String): User
}
interface PostRepository : JpaRepository<Post, Long>{}
