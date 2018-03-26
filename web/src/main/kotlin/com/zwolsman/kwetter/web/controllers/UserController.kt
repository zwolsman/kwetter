package com.zwolsman.kwetter.web.controllers

import com.zwolsman.kwetter.dao.clients.KweetClient
import com.zwolsman.kwetter.dao.clients.UserClient
import com.zwolsman.kwetter.dao.models.KwetterUser
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
}


open class KwetterUserResource(user: KwetterUser, kweetCount: Int) : ResourceSupport() {

    val username = user.username
    val profileImageUrl = user.profileImageUrl
    val followersCount = user.followersCount
    val friendsCount = user.friendsCount
    val kweetCount = kweetCount
    init {
        links.add(linkTo(methodOn(UserController::class.java).byUsername(username)).withSelfRel())
        links.add(linkTo(methodOn(KweetController::class.java).byUsername(username)).withRel("kweets"))
    }
}