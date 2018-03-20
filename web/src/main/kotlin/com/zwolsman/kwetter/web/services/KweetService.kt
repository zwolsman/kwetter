package com.zwolsman.kwetter.web.services

import com.zwolsman.kwetter.web.resources.Kweet
import org.springframework.stereotype.Service
import org.springframework.web.client.RestTemplate
import org.springframework.web.util.DefaultUriBuilderFactory

@Service
class KweetService(private val restTemplate: RestTemplate) {

    init {
        restTemplate.uriTemplateHandler = DefaultUriBuilderFactory("http://kweet-service/")

    }

    fun byId(id: String) = restTemplate.getForObject(id, Kweet::class.java)!!
    fun byUser(username: String) = restTemplate.getForObject("{user}/kweets", Array<Kweet>::class.java, username)!!.toList()
    fun createKweet(text: String, userId: String): Kweet = restTemplate.postForObject("", mapOf("text" to text, "userId" to userId), Kweet::class.java)!!
}