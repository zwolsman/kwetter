package com.zwolsman.kwetter.service.account.controllers

import com.zwolsman.kwetter.service.account.models.KwetterUser
import com.zwolsman.kwetter.service.account.services.UserService
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@RestController
class UserController(private val userService: UserService) {
    private val logger = LoggerFactory.getLogger(javaClass)

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun create(@RequestParam username: String, @RequestParam password: String): KwetterUser {
        return userService.register(username, password)
    }

    @PostMapping("login")
    fun login(@RequestParam username: String, @RequestParam password: String): KwetterUser {
        return userService.login(username, password)
    }

    @GetMapping("{name}")
    fun byUsername(@PathVariable name: String) : KwetterUser {
        return userService.findByUsername(name)
    }
}