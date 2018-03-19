package com.zwolsman.kwetter.service.kweet.models

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document(collection = "user")
data class KweetUser(@Id val id: String)