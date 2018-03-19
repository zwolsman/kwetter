package com.zwolsman.kwetter.web.controllers

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController

@RestController("/api/")
class KweetController() {

    @GetMapping("/kweet/{id}")
    fun byId(@PathVariable id: Long) {

    }

    @GetMapping("/{name}/kweets")
    fun byUsername(@PathVariable userName: String) {

    }

}