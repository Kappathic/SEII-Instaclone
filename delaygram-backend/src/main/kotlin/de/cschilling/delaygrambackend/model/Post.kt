package de.cschilling.delaygrambackend.model

import org.hibernate.annotations.Cascade
import org.hibernate.annotations.CascadeType
import javax.persistence.*

@Entity
class Post(
    val description: String,
    @Lob
    var image: ByteArray?,
    @ElementCollection
    var hashtags: Set<String> = setOf()
):BaseEntity()
