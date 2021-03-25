package controller.cschilling.delaygrambackend.Repository

import controller.cschilling.delaygrambackend.model.User
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface UserRepository : CrudRepository<User, Long>