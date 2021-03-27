package de.cschilling.delaygrambackend.model

import com.fasterxml.jackson.annotation.JsonProperty
import javax.persistence.*

@Entity
class User(
    @Id
    @GeneratedValue
    val id: Long,
    @Column(unique = true)
    var username: String,
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    var password: String,
    var role: String?,
    @Column(unique = true)
    var email: String?,
    @Lob
    var profilePic: ByteArray?
) {
    override fun toString(): String {
        return "User(id=$id, username='$username', password='$password', role='$role', email=$email)"
    }
}
