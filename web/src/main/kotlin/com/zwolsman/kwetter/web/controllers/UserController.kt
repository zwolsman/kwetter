package com.zwolsman.kwetter.web.controllers

import com.zwolsman.kwetter.dao.clients.KweetClient
import com.zwolsman.kwetter.dao.clients.UserClient
import com.zwolsman.kwetter.web.resources.KwetterUserResource
import org.slf4j.LoggerFactory
import org.springframework.data.domain.Pageable
import org.springframework.web.bind.annotation.*
import java.security.Principal

@RestController
@RequestMapping("/api/user/")
class UserController(private val userClient: UserClient, private val kweetClient: KweetClient) {

    private val logger = LoggerFactory.getLogger(this::class.java)

    @GetMapping("timeline")
    fun getTimeline(principal: Principal, pageable: Pageable) = kweetClient.getTimeline(principal.name, pageable)

    @GetMapping("{name}")
    fun byUsername(@PathVariable name: String) = KwetterUserResource(userClient.findByUsername(name), kweetClient.findByUsername(name).size)

    @GetMapping
    fun getSelf(principal: Principal) = byUsername(principal.name)

    @PostMapping
    fun register(@RequestParam username: String, @RequestParam password: String) = KwetterUserResource(userClient.register(username, password), 0)

}