package de.cschilling.delaygrambackend.service

import de.cschilling.delaygrambackend.model.JwtUserDetails
import de.cschilling.delaygrambackend.model.User
import de.cschilling.delaygrambackend.repository.UserRepository
import org.slf4j.Logger
import org.springframework.data.repository.findByIdOrNull
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import org.springframework.web.multipart.MultipartFile
import java.lang.Exception
import kotlin.NoSuchElementException

@Service
class UserService(
    val userRepository: UserRepository,
    val passwordEncoder: PasswordEncoder,
    val logger: Logger
) {
    fun getCurrentUser(): User {
        SecurityContextHolder.getContext().authentication.name
            .let { userRepository.findByUsername(it) }
            ?.let { return it } ?: throw Exception("User with matching username and password not found")
    }
    fun createUser(user: User) = userRepository.save(user)
    fun update(user: User) = userRepository.findByIdOrNull(user.id)
                ?.let { userRepository.save(user) }
                ?: throw NoSuchElementException("No entity found with matching id")

    fun getAllUsers() = userRepository.findAll()

    fun getById(id: Long) = userRepository.findByIdOrNull(id)
        ?: throw NoSuchElementException("No matching User found")

    fun deleteById(id: Long) = userRepository.findByIdOrNull(id)
        ?.let { userRepository.deleteById(id) }
        ?: throw NoSuchElementException("No User found with matching id")

    fun updateProfilePic(profilePic: MultipartFile): User {
        val username = (SecurityContextHolder.getContext().authentication.principal as JwtUserDetails).username
        val user = userRepository.findByUsername(username)
            ?: throw NoSuchElementException("No User found with matching id")
        user.profilePic = profilePic.bytes
        return userRepository.save(user)

    }

}

