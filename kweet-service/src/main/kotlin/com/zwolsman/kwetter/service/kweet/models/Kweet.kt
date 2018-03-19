package com.zwolsman.kwetter.service.kweet.models

import org.bson.types.ObjectId
import org.springframework.data.annotation.Id
import java.util.*

data class Kweet(val text: String, val createdAt: Date = Date(), val entities: Map<String, List<KweetEntity>> = mapEntities(text)) {

    @Id
    lateinit var id: ObjectId

    companion object {
        private val hashtagRegex = Regex("(?:\\s|\\A|^)[##]+([A-Za-z0-9-_]+)", RegexOption.MULTILINE)
        private val userMentionRegex = Regex("(?<=^|(?<=[^a-zA-Z0-9-.]))@[A-Za-z0-9-]+(?=[^a-zA-Z0-9-_.])", RegexOption.MULTILINE)

        fun mapEntities(text: String): Map<String, List<KweetEntity>> {
            println("Matching entities..")
            val map = mutableMapOf<String, List<KweetEntity>>()
            map["hashtags"] = hashtagRegex.findAll(text).map { it.groups[0]!! }.map { Hashtag(it.range, it.value) }.toList()
            map["userMentions"] = userMentionRegex.findAll(text).map { it.groups[0]!! }.map { UserMention(it.range, it.value) }.toList()
            return map
        }
    }
}