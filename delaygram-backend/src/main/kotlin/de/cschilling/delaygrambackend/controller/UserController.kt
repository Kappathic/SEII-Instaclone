package de.cschilling.delaygrambackend.controller

import de.cschilling.delaygrambackend.model.User
import de.cschilling.delaygrambackend.service.JwtUserDetailsService
import de.cschilling.delaygrambackend.service.UserService
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.security.authentication.AuthenticationProvider
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.web.bind.annotation.*
import org.slf4j.Logger
import org.springframework.http.HttpStatus
import org.springframework.web.multipart.MultipartFile
import org.springframework.web.server.ResponseStatusException
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@RestController
@RequestMapping("api/user")
class UserController(
    val userService: UserService
){
    @PreAuthorize("isAuthenticated()")
    @GetMapping("")
    fun getAllUsers() = userService.getAllUsers()

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/{id}")
    fun getUserById(@PathVariable id: Long) = userService.getById(id)

    @PreAuthorize("isAuthenticated()")
    @PostMapping("")
    fun createUser() = userService.getAllUsers()

    @PreAuthorize("isAuthenticated()")
    @PutMapping("")
    fun updateUser(@RequestBody user: User) = userService.update(user)

    @PreAuthorize("isAuthenticated()")
    @DeleteMapping("/{id}")
    fun deleteUserById(@PathVariable id: Long) = userService.deleteById(id)

    @PreAuthorize("isAuthenticated()")
    @PostMapping("/updateProfilePic")
    fun updateProfilePic(
        @RequestParam("picture") profilePic : MultipartFile,
    ) = userService.updateProfilePic(profilePic)

    @PreAuthorize("isAuthenticated()")
    @PostMapping("/follow/{id}")
    fun followUser(
        @PathVariable id: Long
    ) = userService.followUser(id);

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/follower/{id}")
    fun getFollower(
        @PathVariable id: Long
    ) = userService.getFollower(id)
}
