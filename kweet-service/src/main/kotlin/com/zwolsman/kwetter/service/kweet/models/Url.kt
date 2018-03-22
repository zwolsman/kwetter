package com.zwolsman.kwetter.service.kweet.models

data class Url(override val range: Array<Int>, val url: String, val expandedUrl: String) : KweetEntity {
    val displayUrl: String
        get() = expandedUrl.substringAfter("")
}