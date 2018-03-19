package com.zwolsman.kwetter.web.resources

import org.springframework.hateoas.ResourceSupport

open class Kweet(val content: String) : ResourceSupport() {
    companion object {
        var counter: Long = 0
    }

    val id: Long

    init {
        id = counter++
    }
}