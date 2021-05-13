package de.cschilling.delaygrambackend.controller

import de.cschilling.delaygrambackend.model.User
import de.cschilling.delaygrambackend.service.JwtUserDetailsService
import de.cschilling.delaygrambackend.service.UserService
import de.cschilling.delaygrambackend.security.SecurityConfig
import org.slf4j.Logger
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.security.authentication.AuthenticationProvider
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.web.bind.annotation.*
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@RestController
@RequestMapping("api/auth")
class AuthenticationController(
    private val userService: UserService,
    private val authenticationProvider: AuthenticationProvider,
    private val jwtUserDetailsService: JwtUserDetailsService,
    private val securityConfig: SecurityConfig,
    val logger: Logger
) {

    @PreAuthorize("permitAll()")
    @PostMapping("/login")
    fun login(@RequestBody credentials: Map<String, String>, response: HttpServletResponse): User {
        val authenticationToken = UsernamePasswordAuthenticationToken(credentials["username"], credentials["password"])
        SecurityContextHolder.getContext().authentication = authenticationProvider.authenticate(authenticationToken)
        val user = userService.getCurrentUser()
        logger.info(user.toString())
        response.setHeader(
            "Set-Cookie",
            "JWT=Bearer ${jwtUserDetailsService.createToken(user)}; Secure; HttpOnly; SameSite=Strict;Max-Age=60000;Path=/ "
        )
        return user
    }

    @PreAuthorize("permitAll()")
    @PostMapping("/register")
    fun register(@RequestBody user: User):User{
        user.password = securityConfig.passwordEncoder().encode(user.password)
        return userService.createUser(user)

    }
    @PreAuthorize("isAuthenticated()")
    @PostMapping("/logout")
    fun logout(response: HttpServletResponse, request: HttpServletRequest): String {
        val jwtToken = request.getHeader("cookie").substringAfter("Bearer ")
        response.setHeader("Set-Cookie", "JWT=Bearer ${jwtToken}; Secure; HttpOnly; SameSite=Strict;Max-Age=-1;Path=/")
        return "{\"logout\": \"true\"}"
    }

    @PreAuthorize("permitAll()")
    @GetMapping("/authorities")
    fun getAuthorities(): String {
        val roles = SecurityContextHolder.getContext().authentication.authorities.joinToString { it -> it.authority }
        return "{ \"roles\": \"$roles\" }"
    }
}
