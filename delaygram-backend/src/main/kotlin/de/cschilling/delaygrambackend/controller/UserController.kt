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
    val userService: UserService,
    val authenticationProvider: AuthenticationProvider,
    val jwtUserDetailsService: JwtUserDetailsService,
    val logger: Logger
) {


    @PreAuthorize("permitAll()")
    @GetMapping("/authorities")
    fun getAuthorities(): String? {
        try {
            val roles =  SecurityContextHolder.getContext().authentication.authorities.joinToString { it -> it.authority }
            return "{ \"roles\": \"$roles\" }"
        } catch (e: Error) {
            logger.error(e.toString())
            throw ResponseStatusException(
                HttpStatus.BAD_REQUEST,
                e.localizedMessage
            )
        }

    }

    @PreAuthorize("permitAll()")
    @PostMapping("/login")
    fun login(@RequestBody credentials: Map<String, String>, response: HttpServletResponse): User {
        val authenticationToken = UsernamePasswordAuthenticationToken(credentials["username"], credentials["password"])
        SecurityContextHolder.getContext().authentication = authenticationProvider.authenticate(authenticationToken)
        val user = userService.getCurrentUser()
        response.setHeader("Set-Cookie","Bearer ${jwtUserDetailsService.createToken(user)}; Secure; HttpOnly; SameSite=Strict;Max-Age=600;Path=/ ")
        return user
    }

    @PreAuthorize("permitAll()")
    @PostMapping("/register")
    fun register(@RequestBody user: User) = userService.createUser(user);


    @PreAuthorize("isAuthenticated()")
    @PostMapping("/updateProfilePic")
    fun updateProfilePic(
        @RequestParam("picture") profilePic : MultipartFile,
    ) = userService.updateProfilePic(profilePic)



    fun logout(response: HttpServletResponse, request: HttpServletRequest): String {
        val jwtToken = request.getHeader("cookie").substringAfter("Bearer ")
        response.setHeader("Set-Cookie","Bearer ${jwtToken}; Secure; HttpOnly; SameSite=Strict;Max-Age=-1;Path=/")
        return "{\"logout\": \"true\"}"
    }

}
