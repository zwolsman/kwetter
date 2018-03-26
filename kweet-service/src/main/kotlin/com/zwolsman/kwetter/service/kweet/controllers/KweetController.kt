package com.zwolsman.kwetter.service.kweet.controllers

import com.zwolsman.kwetter.dao.models.Kweet
import com.zwolsman.kwetter.service.kweet.services.KweetService
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
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
    fun byUsername(@PathVariable name: String) = kweetService.findByUsername(name)

    @GetMapping("{name}/timeline")
    fun getTimelinePaged(@PathVariable name: String, pageable: Pageable) = kweetService.loadTimeline(name, pageable)

    @GetMapping("{kweetId}")
    fun byId(@PathVariable kweetId: String) = kweetService.findById(kweetId)
}