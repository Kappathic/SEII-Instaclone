package de.cschilling.delaygrambackend.controller

import de.cschilling.delaygrambackend.model.Comment
import de.cschilling.delaygrambackend.model.Post
import de.cschilling.delaygrambackend.repository.PostRepository
import de.cschilling.delaygrambackend.service.PostService
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.*
import org.springframework.web.multipart.MultipartFile

/**
 * Post controller
 *
 * @property postService
 * @constructor Create empty Post controller
 */
@RestController
@RequestMapping("api/post")
class PostController(
    private val postService: PostService
) {
    /**
     * Get all posts
     *@return list of posts
     */
    @PreAuthorize("isAuthenticated()")
    @GetMapping("")
    fun getAllPosts() = postService.getAllPosts()

    /**
     * Get post by id
     *
     * @param id
     * @return post with matching id
     */
    @PreAuthorize("isAuthenticated()")
    @GetMapping("/{id}")
    fun getPostById(@PathVariable id: Long) = postService.getById(id)

    /**
     * Create post
     *
     * @param post
     * @return created post
     */
    @PreAuthorize("isAuthenticated()")
    @PostMapping("")
    fun createPost(@RequestBody post: Post) = postService.createPost(post)

    /**
     * Update post
     *
     * @param post
     * @return updated post
     */
    @PreAuthorize("isAuthenticated()")
    @PatchMapping("")
    fun updatePost(@RequestBody post: Post) = postService.update(post)

    /**
     * Delete post by id
     *
     * @param id
     * @return deleted post
     */
    @PreAuthorize("isAuthenticated()")
    @DeleteMapping("/{id}")
    fun deletePostById(@PathVariable id: Long) = postService.deleteById(id)

    /**
     * Get feed posts
     *@return list of posts
     */
    @PreAuthorize("isAuthenticated()")
    @GetMapping("/feed")
    fun getFeedPosts() = postService.getFeedPosts()

    /**
     * Add comment to post
     *
     * @param id
     * @param comment
     * @return post which received a comment
     */
    @PreAuthorize("isAuthenticated()")
    @PostMapping("/comment/{id}")
    fun addCommentToPost(@PathVariable id: Long,
                         @RequestBody comment: Comment) = postService.addCommentToPost(id, comment)

    /**
     * Like post
     *
     * @param id
     * @return post which got a like
     */
    @PreAuthorize("isAuthenticated()")
    @PostMapping("/like/{id}")
    fun likePost(@PathVariable id: Long) = postService.likePost(id)

    /**
     * Revoke like post
     *
     * @param id
     * @return post which lost a like
     */
    @PreAuthorize("isAuthenticated()")
    @PostMapping("/revokelike/{id}")
    fun revokeLikePost(@PathVariable id: Long) = postService.revokeLikePost(id)


}
