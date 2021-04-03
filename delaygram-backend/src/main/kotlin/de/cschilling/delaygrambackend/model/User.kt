package de.cschilling.delaygrambackend.model

import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.databind.annotation.JsonSerialize
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
    var profilePic: ByteArray?,
    @JoinTable(
        name = "follow",
        joinColumns = [JoinColumn(name = "follower", referencedColumnName = "id", nullable = false)],
        inverseJoinColumns = [JoinColumn(name = "follows",referencedColumnName = "id", nullable = false)]
    )
    @ManyToMany
    @JsonIgnore
    var follower: MutableSet<User> = mutableSetOf(),
    @ManyToMany(mappedBy = "follower")
    var follows: MutableSet<User> = mutableSetOf()
) {
    override fun toString(): String {
        return "User(id=$id, username='$username', password='$password', role=$role, email=$email, profilePic=${profilePic?.contentToString()}, follower=$follower)"
    }
}
