package com.zwolsman.kwetter.web.controllers

import com.zwolsman.kwetter.dao.clients.KweetClient
import com.zwolsman.kwetter.dao.models.Kweet
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import java.security.Principal

@RestController
@RequestMapping("/api/")
class KweetController(private val kweetClient: KweetClient) {

    @GetMapping("/kweet/{id}")
    fun byId(@PathVariable id: String) = kweetClient.findById(id)
    @GetMapping("/user/{name}/kweets")
    fun byUsername(@PathVariable name: String) = kweetClient.findByUsername(name)

    @PostMapping("kweet")
    @ResponseStatus(HttpStatus.CREATED)
    fun createKweet(@RequestParam text: String, principal: Principal) : Kweet = kweetClient.createKweet(text, principal.name)
}