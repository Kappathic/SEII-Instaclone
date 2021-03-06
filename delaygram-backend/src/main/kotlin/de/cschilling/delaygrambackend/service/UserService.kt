package de.cschilling.delaygrambackend.service

import de.cschilling.delaygrambackend.model.User
import de.cschilling.delaygrambackend.repository.UserRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Service
import org.springframework.web.multipart.MultipartFile

@Service
class UserService(
    val userRepository: UserRepository
    ){
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

    fun getCurrentUser(): User {
        SecurityContextHolder.getContext().authentication.name
            .let { userRepository.findByUsername(it) }
            .let { return it }
    }

    fun updateProfilePic(profilePic: MultipartFile): User {
        val user = getCurrentUser()
        user.profilePic = profilePic.bytes
        return userRepository.save(user)

    }

    fun getFollower(id: Long): Set<User> {
        val user = userRepository.findByIdOrNull(id)
            ?: throw NoSuchElementException("No User found with matching id")
        return user.followerUserId
    }

    fun followUser(id: Long): User {
        val user = getCurrentUser()
        val userToFollow = userRepository.findByIdOrNull(id)
            ?: throw NoSuchElementException("No User found with matching id")
        if (user.id == userToFollow.id) {
            throw IllegalArgumentException("You can't follow yourself")
        }
        userToFollow.followerUserId.add(user)
        userRepository.save(userToFollow)
        return userToFollow
    }

    fun getUserByUsername(username: String) = userRepository.findByUsername(username)

    fun unfollowUser(id: Long): Any {
        val user = getCurrentUser()
        val userToFollow = userRepository.findByIdOrNull(id)
            ?: throw NoSuchElementException("No User found with matching id")
        if (user.id == userToFollow.id) {
            throw IllegalArgumentException("You can't unfollow yourself")
        }
        userToFollow.followerUserId.remove(user)
        userRepository.save(userToFollow)
        return userToFollow
    }

}

