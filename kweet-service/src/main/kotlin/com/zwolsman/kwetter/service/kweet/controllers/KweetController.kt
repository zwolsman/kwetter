package com.zwolsman.kwetter.service.kweet.controllers

import com.zwolsman.kwetter.service.kweet.models.Kweet
import com.zwolsman.kwetter.service.kweet.services.KweetService
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController

@RestController
class KweetController(private val kweetService: KweetService) {

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun create(@RequestParam text: String, @RequestParam userId: Int) : Kweet {
        return kweetService.createKweet(text)
    }
}