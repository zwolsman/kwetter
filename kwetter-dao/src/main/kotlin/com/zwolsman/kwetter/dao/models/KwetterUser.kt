package com.zwolsman.kwetter.dao.models

import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonProperty
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.DBRef
import org.springframework.data.mongodb.core.mapping.Document

@Document(collection = "user")
@JsonInclude(JsonInclude.Include.NON_NULL)
data class KwetterUser(@Id
                       val username: String,
                       //@JsonProperty(access = JsonProperty.Access.WRITE_ONLY) TODO make this configurable
                       val password: String = "",
                       val profileImageUrl: String = "http://via.placeholder.com/200x200",
                       var followersCount:Int = 0,
                       var friendsCount: Int = 0,
                       @DBRef
                       @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
                       val friends:MutableSet<KwetterUser> = mutableSetOf()
) {
    override fun equals(other: Any?): Boolean {
        if (other is KwetterUser) {
            return other.username == username
        }
        return super.equals(other)
    }
}