package com.zwolsman.kwetter.web.controllers

import com.zwolsman.kwetter.web.services.KweetService
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/")
class KweetController(private val kweetService: KweetService) {

    @GetMapping("/kweet/{id}")
    fun byId(@PathVariable id: String) = kweetService.byId(id)

    @GetMapping("/{name}/kweets")
    fun byUsername(@PathVariable name: String) = kweetService.byUser(name)

    @PostMapping("kweet")
    @ResponseStatus(HttpStatus.CREATED)
    fun createKweet(@RequestParam text: String, @RequestParam userId: String) = kweetService.createKweet(text, userId)
}