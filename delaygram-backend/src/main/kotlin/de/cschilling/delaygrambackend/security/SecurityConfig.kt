package de.cschilling.delaygrambackend.security

import com.auth0.jwt.JWT
import com.auth0.jwt.JWTVerifier
import com.auth0.jwt.algorithms.Algorithm
import de.cschilling.delaygrambackend.service.JwtUserDetailsService
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.authentication.AuthenticationProvider
import org.springframework.security.authentication.dao.DaoAuthenticationProvider
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder

@Configuration
class SecurityConfig(
    val securityProperties: SecurityProperties
) {


    @Bean
    fun jwtAlgorithm(): Algorithm? {
        return Algorithm.HMAC512(securityProperties.tokenSecret)
    }

    @Bean
    fun verifier(algorithm: Algorithm?): JWTVerifier {
        return JWT
            .require(algorithm)
            .withIssuer(securityProperties.tokenIssuer)
            .build()
    }

    @Bean
    fun passwordEncoder(): PasswordEncoder? {
        return BCryptPasswordEncoder(securityProperties.passwordStrength)
    }

    @Bean
    fun authenticationProvider(jwtUserDetailsService: JwtUserDetailsService?, passwordEncoder: PasswordEncoder?): AuthenticationProvider? {
        val provider = DaoAuthenticationProvider()
        provider.setUserDetailsService(jwtUserDetailsService)
        provider.setPasswordEncoder(passwordEncoder)
        return provider
    }
}
