package com.zwolsman.kwetter.web.controllers

import com.zwolsman.kwetter.web.services.UserService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController

@RestController("api/user/")
class UserController(private val userService: UserService) {

    @GetMapping("{name}")
    fun byUsername(@PathVariable name: String) = userService.findByUsername(name)

}