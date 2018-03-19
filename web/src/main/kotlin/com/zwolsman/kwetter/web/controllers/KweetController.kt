package com.zwolsman.kwetter.web.controllers

import com.zwolsman.kwetter.web.resources.Kweet
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/")
class KweetController {

    @GetMapping("/kweet/{id}")
    fun byId(@PathVariable id: Long) : Kweet {
        TODO("busy boy")
    }

    @GetMapping("/{name}/kweets")
    fun byUsername(@PathVariable userName: String) : List<Kweet> {
        return emptyList()
    }

}