package de.cschilling.delaygrambackend

import de.cschilling.delaygrambackend.model.JwtUserDetails
import de.cschilling.delaygrambackend.model.User
import de.cschilling.delaygrambackend.repository.UserRepository
import de.cschilling.delaygrambackend.security.JWTPreAuthenticationToken
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.authority.AuthorityUtils
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter.event

import org.springframework.security.core.context.SecurityContextHolder

import org.springframework.security.core.context.SecurityContext
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource


@SpringBootApplication
class DelaygramBackendApplication(
    val userRepository: UserRepository
) : CommandLineRunner {
    override fun run(vararg args: String?) {
        try{
            SecurityContextHolder.getContext().authentication = JWTPreAuthenticationToken(
                JwtUserDetails("system",
                    "system",
                    AuthorityUtils.createAuthorityList("admin") as List<SimpleGrantedAuthority>,""),null)
            userRepository.save(User(1L, "admin", "\$2y\$10\$mt1Ev5vlAx2/RZrlFicF1uQNJk3SCGiCYLn.exBGEHL09hwWJfUNi", "admin", "admin@example.com",null,
                mutableSetOf()))
            userRepository.save(User(2L, "user", "\$2y\$10\$mt1Ev5vlAx2/RZrlFicF1uQNJk3SCGiCYLn.exBGEHL09hwWJfUNi", "user", "user@example.com",null,
                mutableSetOf()))
        }finally {
            SecurityContextHolder.clearContext();
        }

    }

}

fun main(args: Array<String>) {
    runApplication<DelaygramBackendApplication>(*args)
}
