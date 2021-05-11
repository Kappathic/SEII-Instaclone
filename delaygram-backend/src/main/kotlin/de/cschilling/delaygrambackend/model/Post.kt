package de.cschilling.delaygrambackend.model

import com.fasterxml.jackson.annotation.*
import java.util.*
import javax.persistence.*

@Entity
class Post(
    val description: String?,
    @Lob
    var image: ByteArray?,
    val creationDate: Date? = Date(),
    @OneToMany(cascade = [CascadeType.ALL])
    var comments: MutableList<Comment>?,
    @ManyToMany(cascade=[CascadeType.PERSIST,CascadeType.MERGE])
    @JoinTable(name = "post_like",
        joinColumns = [JoinColumn(name = "post_id")],
        inverseJoinColumns = [JoinColumn(name = "user_id")])
    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator::class, property = "id")
    @JsonIdentityReference(alwaysAsId=true)
    var likesUserId: MutableSet<User>?,
    @ElementCollection
    var hashtags: Set<String> = setOf()
):BaseEntity()
