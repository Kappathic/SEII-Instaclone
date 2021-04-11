package de.cschilling.delaygrambackend.service

import de.cschilling.delaygrambackend.model.Post
import de.cschilling.delaygrambackend.model.User
import de.cschilling.delaygrambackend.repository.PostRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service


@Service
class PostService(
    val postRepository: PostRepository,
    val userService: UserService
) {
    fun createPost(post: Post): Post {
        val savedPost = postRepository.save(post)
        userService.getCurrentUser().posts.add(savedPost)
        return savedPost
    }
        fun update(post: Post) = postRepository.findByIdOrNull(post.id)
            ?.let { postRepository.save(post) }
            ?: throw NoSuchElementException("No entity found with matching id")

        fun getAllPosts(): MutableList<Post> = postRepository.findAll()

        fun getById(id: Long) = postRepository.findByIdOrNull(id)
            ?: throw NoSuchElementException("No matching Post found")

        fun deleteById(id: Long) = postRepository.findByIdOrNull(id)
            ?.let { postRepository.deleteById(id) }
            ?: throw NoSuchElementException("No Post found with matching id")
}
