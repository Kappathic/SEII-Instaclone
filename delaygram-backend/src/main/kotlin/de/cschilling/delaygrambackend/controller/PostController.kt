package de.cschilling.delaygrambackend.controller

import de.cschilling.delaygrambackend.model.Comment
import de.cschilling.delaygrambackend.model.Post
import de.cschilling.delaygrambackend.repository.PostRepository
import de.cschilling.delaygrambackend.service.PostService
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.*
import org.springframework.web.multipart.MultipartFile

@RestController
@RequestMapping("api/post")
class PostController(
    private val postService: PostService
) {
    @PreAuthorize("isAuthenticated()")
    @GetMapping("")
    fun getAllPosts() = postService.getAllPosts()

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/{id}")
    fun getPostById(@PathVariable id: Long) = postService.getById(id)

    @PreAuthorize("isAuthenticated()")
    @PostMapping("")
    fun createPost(@RequestBody post: Post) = postService.createPost(post)

    @PreAuthorize("isAuthenticated()")
    @PutMapping("")
    fun updatePost(@RequestBody post: Post) = postService.update(post)

    @PreAuthorize("isAuthenticated()")
    @DeleteMapping("/{id}")
    fun deletePostById(@PathVariable id: Long) = postService.deleteById(id)

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/feed")
    fun getFeedPosts() = postService.getFeedPosts()

    @PreAuthorize("isAuthenticated()")
    @PostMapping("/comment/{id}")
    fun addCommentToPost(@PathVariable id: Long,
                         @RequestBody comment: Comment) = postService.addCommentToPost(id, comment)

    @PreAuthorize("isAuthenticated()")
    @PostMapping("/like/{id}")
    fun likePost(@PathVariable id: Long) = postService.likePost(id)

}
