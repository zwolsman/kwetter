package com.zwolsman.kwetter.web.resources

import com.zwolsman.kwetter.web.controllers.KweetController
import com.zwolsman.kwetter.web.controllers.UserController
import org.springframework.hateoas.Link
import org.springframework.hateoas.ResourceSupport
import org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo
import org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn
import java.util.*


open class KwetterUser(val username: String,
                       val location: String? = null,
                       val url: String? = null,
                       val description: String? = null,
                       val createdAt: Date,
                       val profileImageUrl: String = "http://via.placeholder.com/200x200",
                       var followersCount: Int = 0,
                       var friendsCount: Int = 0,
                       val friends: MutableSet<KwetterUser> = mutableSetOf()) : ResourceSupport() {
    init {
        this.add(linkTo(methodOn(UserController::class.java).byUsername(username)).withSelfRel())
        this.add(linkTo(methodOn(KweetController::class.java).byUsername(username)).withRel("kweets").expand(username))
        this.add(linkTo(methodOn(KweetController::class.java).byUsername(username)).withRel("followers").expand(username))
        this.add(linkTo(methodOn(KweetController::class.java).byUsername(username)).withRel("following").expand(username))
    }
}