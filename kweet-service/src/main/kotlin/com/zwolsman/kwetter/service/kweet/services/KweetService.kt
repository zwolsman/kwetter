package com.zwolsman.kwetter.service.kweet.services

import com.zwolsman.kwetter.dao.clients.UserClient
import com.zwolsman.kwetter.dao.models.Kweet
import com.zwolsman.kwetter.service.kweet.exceptions.KweetNotFoundException
import com.zwolsman.kwetter.service.kweet.repositories.KweetRepository
import org.bson.types.ObjectId
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.domain.Sort
import org.springframework.data.mongodb.core.MongoTemplate
import org.springframework.data.mongodb.core.query.Criteria
import org.springframework.data.mongodb.core.query.Query
import org.springframework.data.repository.support.PageableExecutionUtils
import org.springframework.stereotype.Service

@Service
class KweetService(private val kweetRepository: KweetRepository, private val userClient: UserClient, private val template: MongoTemplate) {

    fun createKweet(text: String, userId: String): Kweet {
        val user = userClient.findByUsername(userId)
        val kweet = Kweet(text, user)
        return kweetRepository.save(kweet)
    }

    fun findByUsername(name: String) = kweetRepository.findByUserOrderByIdDesc(name)
    fun findById(kweetId: String) = kweetRepository.findById(ObjectId(kweetId)).orElseThrow { throw KweetNotFoundException(kweetId) }

    fun loadTimeline(name: String, pageable: Pageable): Page<Kweet> {
        val user = userClient.findByUsername(name)
        val users = listOf(*user.friends.toTypedArray(), user)
        val qur = Query(Criteria.where("user").`in`(users))
        val kweets = template.find(qur.with(pageable).with(Sort.by(Sort.Order.desc("id"))), Kweet::class.java)

        return PageableExecutionUtils.getPage(kweets, pageable, { template.count(qur, Kweet::class.java) })
    }

}
