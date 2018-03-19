package com.zwolsman.kwetter.service.kweet.services

import com.zwolsman.kwetter.service.kweet.models.Kweet
import com.zwolsman.kwetter.service.kweet.models.KweetUser
import com.zwolsman.kwetter.service.kweet.repositories.KweetRepository
import org.springframework.stereotype.Service

@Service
class KweetService(private val kweetRepository: KweetRepository) {

    fun createKweet(text: String, userId: String): Kweet {
        val kweet =  Kweet(text)
        kweet.user = KweetUser(userId)
        return kweetRepository.save(kweet)
    }

}