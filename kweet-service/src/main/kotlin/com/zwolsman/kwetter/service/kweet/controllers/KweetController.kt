package com.zwolsman.kwetter.service.kweet.controllers

import com.zwolsman.kwetter.service.kweet.models.Kweet
import com.zwolsman.kwetter.service.kweet.services.KweetService
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@RestController
class KweetController(private val kweetService: KweetService) {

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun create(@RequestParam text: String, @RequestParam userId: String): Kweet {
        return kweetService.createKweet(text, userId)
    }

    @GetMapping("{name}/kweets")
    fun byUsername(@RequestParam name: String) = kweetService.findByUsername(name)

    @GetMapping("{kweetId}")
    fun byId(@RequestParam kweetId: String) = kweetService.findById(kweetId)
}