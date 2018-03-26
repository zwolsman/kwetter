package com.zwolsman.kwetter.web.resources

import com.zwolsman.kwetter.dao.models.KwetterUser
import com.zwolsman.kwetter.web.controllers.KweetController
import com.zwolsman.kwetter.web.controllers.UserController
import org.springframework.hateoas.ResourceSupport
import org.springframework.hateoas.mvc.ControllerLinkBuilder

open class KwetterUserResource(user: KwetterUser, kweetCount: Int) : ResourceSupport() {

    val username = user.username
    val profileImageUrl = user.profileImageUrl
    val followersCount = user.followersCount
    val friendsCount = user.friendsCount
    val kweetCount = kweetCount
    init {
        links.add(ControllerLinkBuilder.linkTo(ControllerLinkBuilder.methodOn(UserController::class.java).byUsername(username)).withSelfRel())
        links.add(ControllerLinkBuilder.linkTo(ControllerLinkBuilder.methodOn(KweetController::class.java).byUsername(username)).withRel("kweets"))
    }
}