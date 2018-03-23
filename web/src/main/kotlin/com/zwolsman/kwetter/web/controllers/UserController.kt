package com.zwolsman.kwetter.web.controllers

import com.zwolsman.kwetter.dao.clients.KweetClient
import com.zwolsman.kwetter.dao.clients.UserClient
import com.zwolsman.kwetter.dao.models.KwetterUser
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/user/")
class UserController(private val userClient: UserClient, private val kweetClient: KweetClient) {

    @GetMapping("{name}")
    fun byUsername(@PathVariable name: String) : KwetterUser = userClient.findByUsername(name)

    @GetMapping
    fun getTimeline(@RequestParam user: String = "tester") = kweetClient.getTimeline(user)
}