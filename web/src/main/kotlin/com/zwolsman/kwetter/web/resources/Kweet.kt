//package com.zwolsman.kwetter.web.resources
//
//import com.fasterxml.jackson.annotation.JsonInclude
//import com.zwolsman.kwetter.web.controllers.UserController
//import org.springframework.hateoas.ResourceSupport
//import org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo
//import org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn
//import java.util.*
//
//@JsonInclude(JsonInclude.Include.NON_NULL)
//open class Kweet(val id: String = "not set", val text: String, val createdAt: Date = Date(), val entities: KweetEntities, val user: KwetterUser) : ResourceSupport() {
//    init {
//        this.add(linkTo(methodOn(com.zwolsman.kwetter.web.controllers.KweetController::class.java).byId(id)).withSelfRel())
//        this.add(linkTo(methodOn(UserController::class.java).byUsername(user.username)).withRel("user"))
//    }
//}