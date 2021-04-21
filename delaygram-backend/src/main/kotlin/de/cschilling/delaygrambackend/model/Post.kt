package de.cschilling.delaygrambackend.model

import javax.persistence.*

@Entity
class Post(
    val description: String?,
    @Lob
    var image: ByteArray?,
    @OneToMany(cascade = [CascadeType.ALL])
    var comments: MutableList<Comment>,
    @ElementCollection
    var hashtags: Set<String> = setOf()
):BaseEntity()
