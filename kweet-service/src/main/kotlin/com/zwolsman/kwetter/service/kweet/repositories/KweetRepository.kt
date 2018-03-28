package com.zwolsman.kwetter.service.kweet.repositories

import com.zwolsman.kwetter.dao.models.Kweet
import org.bson.types.ObjectId
import org.springframework.data.mongodb.repository.MongoRepository

interface KweetRepository : MongoRepository<Kweet, ObjectId> {
    fun findByUserOrderByIdDesc(name: String): List<Kweet>
}