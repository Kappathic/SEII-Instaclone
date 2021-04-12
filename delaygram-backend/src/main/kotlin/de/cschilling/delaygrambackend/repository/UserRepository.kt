package de.cschilling.delaygrambackend.repository

import de.cschilling.delaygrambackend.model.User
import org.springframework.data.jpa.repository.JpaRepository

interface UserRepository : JpaRepository<User?, Long?> {
    override fun findAll(): List<User?>
    fun findByUsername(name: String): User?
}
