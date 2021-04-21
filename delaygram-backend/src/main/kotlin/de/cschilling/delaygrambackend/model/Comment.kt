package de.cschilling.delaygrambackend.model

import com.fasterxml.jackson.annotation.JsonBackReference
import java.util.*
import javax.persistence.Entity
import javax.persistence.FetchType
import javax.persistence.JoinColumn
import javax.persistence.OneToOne

@Entity
class Comment(
    var text: String?,
    var creationDate: Date = Date(),
    var userId: Long,
    @OneToOne
    @JoinColumn(
        name = "userId",
        referencedColumnName = "id",
        insertable = false,
        updatable = false)
    @JsonBackReference
    var user: User?
): BaseEntity()
