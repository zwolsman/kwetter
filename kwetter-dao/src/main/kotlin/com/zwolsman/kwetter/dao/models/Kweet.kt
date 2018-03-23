package com.zwolsman.kwetter.dao.models

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.DBRef
import org.springframework.data.mongodb.core.mapping.Document
import java.util.*

@Document
data class Kweet(@Id val id: String? = null, val text: String, val createdAt: Date = Date(), val entities: KweetEntities) {
    @DBRef
    lateinit var user:KwetterUser


    constructor(text: String, user: KwetterUser) : this(null, text, Date(), mapEntities(text)) {
        this.user = user
    }

    companion object {
        private val hashtagRegex = Regex("(?:\\s|\\A|^)[##]+([A-Za-z0-9-_]+)")
        private val userMentionRegex = Regex("(?<=^|(?<=[^a-zA-Z0-9-.]))@[A-Za-z0-9-]+(?=[^a-zA-Z0-9-_.])")
        private val urlRegex = Regex("(https?://(?:www\\.|(?!www))[a-zA-Z0-9][a-zA-Z0-9-]+[a-zA-Z0-9]\\.[^\\s]{2,}|www\\.[a-zA-Z0-9][a-zA-Z0-9-]+[a-zA-Z0-9]\\.[^\\s]{2,}|https?://(?:www\\.|(?!www))[a-zA-Z0-9]\\.[^\\s]{2,}|www\\.[a-zA-Z0-9]\\.[^\\s]{2,})")

        fun mapEntities(text: String): KweetEntities {
            println("Matching entities..")
            val entities = KweetEntities()
            entities.hashtags = hashtagRegex.findAll(text).map { it.groups[0]!! }.map { Hashtag(arrayOf(it.range.first, it.range.last), it.value) }.toList()
            entities.userMentions = userMentionRegex.findAll(text).map { it.groups[0]!! }.map { UserMention(arrayOf(it.range.first, it.range.last), it.value) }.toList()
            entities.urls = urlRegex.findAll(text).map { it.groups[0]!! }.map {
                val shortUrl = "<short url>"//UrlShortenerService().getShortUrl(it.value)
                Url(arrayOf(it.range.first, it.range.last), shortUrl, it.value)
            }.toList()
            return entities
        }
    }
}