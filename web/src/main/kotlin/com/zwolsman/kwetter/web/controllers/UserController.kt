package com.zwolsman.kwetter.web.controllers

import com.zwolsman.kwetter.dao.clients.KweetClient
import com.zwolsman.kwetter.dao.clients.UserClient
import com.zwolsman.kwetter.dao.models.KwetterUser
import com.zwolsman.kwetter.web.resources.KwetterUserResource
import org.springframework.hateoas.ResourceSupport
import org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo
import org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/user/")
class UserController(private val userClient: UserClient, private val kweetClient: KweetClient) {

    @GetMapping("{name}")
    fun byUsername(@PathVariable name: String) = KwetterUserResource(userClient.findByUsername(name), kweetClient.findByUsername(name).size)

    @GetMapping
    fun getTimeline(@RequestParam user: String = "tester") = kweetClient.getTimeline(user)

    @PostMapping
    fun register(@RequestParam username: String, @RequestParam password: String) = KwetterUserResource(userClient.register(username, password), 0)

}