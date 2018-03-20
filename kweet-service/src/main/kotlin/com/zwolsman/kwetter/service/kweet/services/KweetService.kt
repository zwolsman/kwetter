package com.zwolsman.kwetter.service.kweet.services

import com.zwolsman.kwetter.service.kweet.exceptions.KweetNotFoundException
import com.zwolsman.kwetter.service.kweet.models.Kweet
import com.zwolsman.kwetter.service.kweet.models.KweetUser
import com.zwolsman.kwetter.service.kweet.repositories.KweetRepository
import org.bson.types.ObjectId
import org.springframework.stereotype.Service

@Service
class KweetService(private val kweetRepository: KweetRepository) {

    fun createKweet(text: String, userId: String): Kweet {
        val kweet = Kweet(text)
        kweet.user = KweetUser(userId)
        return kweetRepository.save(kweet)
    }

    fun findByUsername(name: String) = kweetRepository.findByUser(name)
    fun findById(kweetId: String) = kweetRepository.findById(ObjectId(kweetId)).orElseThrow { throw KweetNotFoundException(kweetId) }

}
