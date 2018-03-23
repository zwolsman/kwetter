package com.zwolsman.kwetter.service.account.repositories

import com.zwolsman.kwetter.dao.models.KwetterUser
import org.bson.types.ObjectId
import org.springframework.data.mongodb.repository.MongoRepository

interface UserRepository : MongoRepository<KwetterUser, String> {
    fun findByUsername(username: String) : KwetterUser?
}