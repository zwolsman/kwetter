package com.zwolsman.kwetter.web.controllers

import com.zwolsman.kwetter.dao.clients.UserClient
import com.zwolsman.kwetter.dao.models.KwetterUser
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/user/")
class UserController(private val userClient: UserClient) {

    @GetMapping("{name}")
    fun byUsername(@PathVariable name: String) : KwetterUser = userClient.findByUsername(name)

}