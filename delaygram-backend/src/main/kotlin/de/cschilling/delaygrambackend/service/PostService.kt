package de.cschilling.delaygrambackend.service

import de.cschilling.delaygrambackend.model.Comment
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
        val user = userService.getCurrentUser()
        user.posts.add(savedPost)
        userService.update(user)
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

    fun getFeedPosts(): MutableList<Post> {
        val posts = mutableListOf<Post>()
        val user = userService.getCurrentUser()
        posts.addAll(user.follows.flatMap {followUser -> followUser.posts })
        return posts
    }

    fun addCommentToPost(id: Long, comment: Comment): Post {
        comment.userId = userService.getCurrentUser()
        val post = postRepository.findByIdOrNull(id)
            ?: throw NoSuchElementException("No matching Post found")
        post.comments?.add(comment)
        return postRepository.save(post)
    }

    fun likePost(id: Long): Post {
        val user = userService.getCurrentUser()
        val post = postRepository.findByIdOrNull(id)
            ?: throw NoSuchElementException("No matching Post found")
        post.likesUserId?.add(user)
        return postRepository.save(post)
    }
}
