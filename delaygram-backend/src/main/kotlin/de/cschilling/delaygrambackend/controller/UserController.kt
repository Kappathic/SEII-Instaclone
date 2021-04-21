package de.cschilling.delaygrambackend.controller

import de.cschilling.delaygrambackend.model.User
import de.cschilling.delaygrambackend.service.UserService
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.*
import org.springframework.web.multipart.MultipartFile

@RestController
@RequestMapping("api/user")
class UserController(
    private val userService: UserService
){
    @PreAuthorize("isAuthenticated()")
    @GetMapping("")
    fun getAllUsers() = userService.getAllUsers()

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/{id}")
    fun getUserById(@PathVariable id: Long) = userService.getById(id)

    @PreAuthorize("isAuthenticated()")
    @PostMapping("")
    fun createUser(@RequestBody user: User) = userService.createUser(user)

    @PreAuthorize("isAuthenticated()")
    @PutMapping("")
    fun updateUser(@RequestBody user: User) = userService.update(user)

    @PreAuthorize("isAuthenticated()")
    @DeleteMapping("/{id}")
    fun deleteUserById(@PathVariable id: Long) = userService.deleteById(id)

    @PreAuthorize("isAuthenticated()")
    @PostMapping("/updateProfilePic")
    fun updateProfilePic(
        @RequestParam("picture") profilePic: MultipartFile,
    ) = userService.updateProfilePic(profilePic)

    @PreAuthorize("isAuthenticated()")
    @PostMapping("/follow/{id}")
    fun followUser(@PathVariable id: Long) = userService.followUser(id)
    @PreAuthorize("isAuthenticated()")
    @PostMapping("/unfollow/{id}")
    fun unfollowUser(@PathVariable id: Long) = userService.unfollowUser(id)

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/follower/{id}")
    fun getFollower(@PathVariable id: Long) = userService.getFollower(id)

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/getFollows/{username}")
    fun getFollows(@PathVariable username: String) = userService.getUserByUsername(username).follows

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/profile/{username}")
    fun getProfile(@PathVariable username: String) = userService.getUserByUsername(username)

}
