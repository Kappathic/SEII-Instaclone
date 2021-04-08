package de.cschilling.delaygrambackend.controller

import de.cschilling.delaygrambackend.model.User
import de.cschilling.delaygrambackend.service.UserService
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.*
import org.springframework.web.multipart.MultipartFile

@RestController
@RequestMapping("api/users")
class UserController(
    val userService: UserService
) {
    @PreAuthorize("isAuthenticated()")
    @PostMapping("/updateProfilePic")
    fun updateProfilePic(
        @RequestParam("picture") profilePic: MultipartFile,
    ) = userService.updateProfilePic(profilePic)

    @PreAuthorize("isAuthenticated()")
    @PostMapping("/follow/{id}")
    fun followUser(
        @PathVariable id: Long
    ) = userService.followUser(id)

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/follower/{id}")
    fun getFollower(
        @PathVariable id: Long
    ) = userService.getFollower(id)
}
