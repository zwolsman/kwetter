package com.zwolsman.kwetter.web.resources

import com.zwolsman.kwetter.web.controllers.KweetController
import com.zwolsman.kwetter.web.controllers.UserController
import org.springframework.hateoas.ResourceSupport
import org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo
import org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn
import java.util.*

data class KwetterUser(val username: String,
                       val location: String? = null,
                       val url: String? = null,
                       val description: String? = null,
                       val createdAt: Date,
                       val profileImageUrl: String = "http://via.placeholder.com/200x200",
                       var followersCount: Int = 0,
                       var friendsCount: Int = 0,
                       val friends: MutableSet<KwetterUser>) : ResourceSupport() {
    init {
        add(linkTo(methodOn(UserController::class.java).byUsername(username)).withSelfRel())
        add(linkTo(methodOn(KweetController::class.java).byUsername(username)).withRel("kweets"))
        add(linkTo(methodOn(KweetController::class.java).byUsername(username)).withRel("followers"))
        add(linkTo(methodOn(KweetController::class.java).byUsername(username)).withRel("following"))
    }
}