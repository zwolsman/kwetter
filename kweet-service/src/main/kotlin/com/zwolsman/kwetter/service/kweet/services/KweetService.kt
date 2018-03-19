package com.zwolsman.kwetter.service.kweet.services

import com.zwolsman.kwetter.service.kweet.models.Kweet
import com.zwolsman.kwetter.service.kweet.repositories.KweetRepository
import org.bson.types.ObjectId
import org.springframework.stereotype.Service

@Service
class KweetService(private val kweetRepository: KweetRepository) {

    fun createKweet(text: String): Kweet {
        val kweet =  Kweet(text)
        kweet.id = ObjectId()
        return kweet
    }

}