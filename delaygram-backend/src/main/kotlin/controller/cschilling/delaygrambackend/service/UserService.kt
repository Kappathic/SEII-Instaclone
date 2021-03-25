package controller.cschilling.delaygrambackend.service

import controller.cschilling.delaygrambackend.Repository.UserRepository
import controller.cschilling.delaygrambackend.model.User
import org.springframework.stereotype.Service

@Service
class UserService(val repository: UserRepository) {

    fun save(user: User) = repository.save(user)

    fun findById(id:Long): String {
        val temp = repository.findById(id)
        return if(temp.isPresent) {
            "User found with the id"
        }
        else{
            "No user found with given id"
        }
    }
}