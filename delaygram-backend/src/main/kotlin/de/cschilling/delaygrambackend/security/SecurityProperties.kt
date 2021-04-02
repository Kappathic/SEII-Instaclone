package de.cschilling.delaygrambackend.security

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.stereotype.Component
import java.time.Duration

@Component
@ConfigurationProperties(prefix = "delaygram.security")
class SecurityProperties {

    val passwordStrength = 10
    val tokenSecret = "productservice-secret"
    val tokenIssuer = "productservice-api"
    val tokenExpiration = Duration.ofHours(4)
}
