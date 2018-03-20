package com.zwolsman.kwetter.service.kweet.repositories

import com.zwolsman.kwetter.service.kweet.models.Kweet
import org.bson.types.ObjectId
import org.springframework.data.mongodb.repository.MongoRepository

interface KweetRepository : MongoRepository<Kweet, ObjectId> {
    fun findByUser(name: String): List<Kweet>
}