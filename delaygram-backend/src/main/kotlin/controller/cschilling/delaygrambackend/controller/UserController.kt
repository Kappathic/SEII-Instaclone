package controller.cschilling.delaygrambackend.controller

import controller.cschilling.delaygrambackend.service.UserService
import controller.cschilling.delaygrambackend.model.User
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/user")
class UserController(val service: UserService) {

    @PostMapping("/add")
    fun addUser(@RequestBody user: User) = service.save(user)

    @GetMapping("/{id}")
    fun getById(@PathVariable id: Long)
            = service.findById(id)
}