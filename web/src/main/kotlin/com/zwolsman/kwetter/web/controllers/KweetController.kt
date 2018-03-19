package com.zwolsman.kwetter.web.controllers

import com.zwolsman.kwetter.web.resources.Kweet
import org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo
import org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController

@RestController("/api/kweet")
class KweetController {

    @GetMapping
    fun test(): Kweet {
        val kweet = Kweet("test content")
        kweet.add(linkTo(methodOn(KweetController::class.java).byId(kweet.id)).withSelfRel())
        return kweet
    }

    @GetMapping("{id}")
    fun byId(@PathVariable id: Long): Kweet {
        val x = Kweet("test content")
        x.add(linkTo(methodOn(KweetController::class.java).byId(x.id)).withSelfRel())
        return x
    }
}