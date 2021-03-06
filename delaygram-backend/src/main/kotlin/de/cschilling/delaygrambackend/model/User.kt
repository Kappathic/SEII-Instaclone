package de.cschilling.delaygrambackend.model

import com.fasterxml.jackson.annotation.*
import org.hibernate.annotations.Formula
import javax.persistence.*

@Entity
class User(
    @Column(unique = true)
    var username: String,
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    var password: String,
    var role: String?,
    var description: String?,
    @Column(unique = true)
    var email: String?,
    @Lob
    var profilePic: ByteArray?,
    @OneToMany(mappedBy = "user")
    var posts: MutableList<Post> = mutableListOf(),
    @Formula(
        """SELECT COUNT(P.USER_ID) 
            FROM USER_POSTS P 
            WHERE (ID = P.USER_ID) GROUP BY ID"""
    )
    var postCount: Int?,
    @JoinTable(
        name = "follow",
        joinColumns = [JoinColumn(name = "followerUserId", referencedColumnName = "id", nullable = false)],
        inverseJoinColumns = [JoinColumn(name = "follows", referencedColumnName = "id", nullable = false)]
    )
    @ManyToMany
    @JsonManagedReference
    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator::class, property = "id")
    @JsonIdentityReference(alwaysAsId=true)
    var followerUserId: MutableSet<User> = mutableSetOf(),
    @ManyToMany(mappedBy = "followerUserId")
    @JsonBackReference
    var follows: MutableSet<User> = mutableSetOf()
):BaseEntity() {
    override fun toString(): String {
        return "User(id=$id, username='$username', password='$password', role=$role, email=$email, profilePic=${profilePic?.contentToString()}, followerUserId=$followerUserId)"
    }
}
