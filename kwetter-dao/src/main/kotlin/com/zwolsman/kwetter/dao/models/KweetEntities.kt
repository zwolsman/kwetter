package com.zwolsman.kwetter.dao.models

data class KweetEntities (val hashtags: List<Hashtag> = emptyList(), val userMentions:List<UserMention> = emptyList(), val urls: List<Url> = emptyList())

data class Hashtag(val range: Array<Int>, val text: String)

data class UserMention(val range: Array<Int>, val name: String)

data class Url(val range: List<Int>, val url: String, val expandedUrl: String) {
    val displayUrl: String
        get() = expandedUrl.substringAfter("")
}