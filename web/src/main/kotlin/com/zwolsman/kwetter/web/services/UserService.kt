package com.zwolsman.kwetter.web.services

import com.zwolsman.kwetter.web.resources.KwetterUser
import org.springframework.stereotype.Service
import org.springframework.web.client.RestTemplate
import org.springframework.web.util.DefaultUriBuilderFactory

@Service
class UserService(private val restTemplate: RestTemplate) {

    init {
        restTemplate.uriTemplateHandler = DefaultUriBuilderFactory("http://account-service/")
    }

    fun findByUsername(name: String) = restTemplate.getForObject("{name}", KwetterUser::class.java, name)!!

}