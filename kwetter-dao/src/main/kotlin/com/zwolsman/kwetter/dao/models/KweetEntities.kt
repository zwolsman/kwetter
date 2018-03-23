package com.zwolsman.kwetter.dao.models

data class KweetEntities (var hashtags: List<Hashtag> = emptyList(), var userMentions:List<UserMention> = emptyList(), var urls: List<Url> = emptyList())

data class Hashtag(val range: Array<Int>, val text: String)

data class UserMention(val range: Array<Int>, val name: String)

data class Url(val range: Array<Int>, val url: String, val expandedUrl: String) {
    val displayUrl: String
        get() = expandedUrl.substringAfter("")
}