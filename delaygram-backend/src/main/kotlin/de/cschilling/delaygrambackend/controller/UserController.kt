package de.cschilling.delaygrambackend.controller

import de.cschilling.delaygrambackend.model.User
import de.cschilling.delaygrambackend.service.UserService
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.*
import org.springframework.web.multipart.MultipartFile

/**
 * User controller
 *
 * @property userService
 * @constructor Create empty User controller
 */
@RestController
@RequestMapping("api/user")
class UserController(
    private val userService: UserService
){
    /**
     * Get all users
     *@return list of users
     */
    @PreAuthorize("isAuthenticated()")
    @GetMapping("")
    fun getAllUsers() = userService.getAllUsers()

    /**
     * Get user by id
     *
     * @param id
     * @return user with matching id
     */
    @PreAuthorize("isAuthenticated()")
    @GetMapping("/{id}")
    fun getUserById(@PathVariable id: Long) = userService.getById(id)

    /**
     * Create user
     *
     * @param user
     * @return created user
     */
    @PreAuthorize("isAuthenticated()")
    @PostMapping("")
    fun createUser(@RequestBody user: User) = userService.createUser(user)

    /**
     * Update user
     *
     * @param user
     * @return updated user
     */
    @PreAuthorize("isAuthenticated()")
    @PatchMapping("")
    fun updateUser(@RequestBody user: User) = userService.update(user)

    /**
     * Delete user by id
     *
     * @param id
     * @return deleted user
     */
    @PreAuthorize("isAuthenticated()")
    @DeleteMapping("/{id}")
    fun deleteUserById(@PathVariable id: Long) = userService.deleteById(id)

    /**
     * Update profile picture
     *
     * @param profilePic
     * @return user who updated his profile pic
     */
    @PreAuthorize("isAuthenticated()")
    @PostMapping("/updateProfilePic")
    fun updateProfilePic(
        @RequestParam("picture") profilePic: MultipartFile,
    ) = userService.updateProfilePic(profilePic)

    /**
     * Follow user
     *
     * @param id
     * @return user which got a follow
     */
    @PreAuthorize("isAuthenticated()")
    @PostMapping("/follow/{id}")
    fun followUser(@PathVariable id: Long) = userService.followUser(id)

    /**
     * Unfollow user
     *
     * @param id
     * @return user which is unfollowed
     */
    @PreAuthorize("isAuthenticated()")
    @PostMapping("/unfollow/{id}")
    fun unfollowUser(@PathVariable id: Long) = userService.unfollowUser(id)

    /**
     * Get follower
     *
     * @param id
     * @return list of userIds
     */
    @PreAuthorize("isAuthenticated()")
    @GetMapping("/follower/{id}")
    fun getFollower(@PathVariable id: Long) = userService.getFollower(id)

    /**
     * Get follows
     *
     * @param username
     * @return list of users
     */
    @PreAuthorize("isAuthenticated()")
    @GetMapping("/getFollows/{username}")
    fun getFollows(@PathVariable username: String) = userService.getUserByUsername(username).follows

    /**
     * Get profile
     *
     * @param username
     * @return user with matching username
     */
    @PreAuthorize("isAuthenticated()")
    @GetMapping("/profile/{username}")
    fun getProfile(@PathVariable username: String) = userService.getUserByUsername(username)

}
