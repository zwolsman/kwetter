package com.zwolsman.kwetter.service.kweet.services

import com.zwolsman.kwetter.dao.clients.UserClient
import com.zwolsman.kwetter.dao.models.Kweet
import com.zwolsman.kwetter.service.kweet.exceptions.KweetNotFoundException
import com.zwolsman.kwetter.service.kweet.repositories.KweetRepository
import org.bson.types.ObjectId
import org.springframework.data.mongodb.core.MongoTemplate
import org.springframework.data.mongodb.core.query.Criteria
import org.springframework.data.mongodb.core.query.Query
import org.springframework.stereotype.Service

@Service
class KweetService(private val kweetRepository: KweetRepository, private val userClient: UserClient, private val template: MongoTemplate) {

    fun createKweet(text: String, userId: String): Kweet {
        val user = userClient.findByUsername(userId)
        val kweet = Kweet(text, user)
        return kweetRepository.save(kweet)
    }

    fun findByUsername(name: String) = kweetRepository.findByUser(name)
    fun findById(kweetId: String) = kweetRepository.findById(ObjectId(kweetId)).orElseThrow { throw KweetNotFoundException(kweetId) }
    fun loadTimeline(name: String): List<Kweet> {
        val user = userClient.findByUsername(name)
        val users = listOf(*user.friends.toTypedArray(), user)
        return template.find(Query(Criteria.where("user").`in`(users)), Kweet::class.java)

    }

}
