package com.zwolsman.kwetter.dao.models

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.DBRef
import org.springframework.data.mongodb.core.mapping.Document
import java.util.*

@Document
data class Kweet(@Id val id: String, val text: String, val createdAt: Date = Date(), val entities: KweetEntities) {
    @DBRef
    lateinit var user:KwetterUser

    constructor(id: String, text: String, createdAt: Date = Date(), entities: KweetEntities, user: KwetterUser) : this(id, text, createdAt, entities) {
        this.user = user
    }
}