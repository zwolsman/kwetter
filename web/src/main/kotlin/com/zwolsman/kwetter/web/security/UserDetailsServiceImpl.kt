package com.zwolsman.kwetter.web.security

import com.zwolsman.kwetter.dao.clients.UserClient
import com.zwolsman.kwetter.dao.exceptions.UserNotFoundException
import com.zwolsman.kwetter.dao.models.KwetterUser
import org.slf4j.LoggerFactory
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder

class UserDetailsServiceImpl(private val userClient: UserClient, private val bCryptPasswordEncoder: BCryptPasswordEncoder) {

    private val logger = LoggerFactory.getLogger(this::class.java)!!
    fun login(username: String, password: String): KwetterUser {
        val user = userClient.findByUsername(username)

        if (bCryptPasswordEncoder.matches(password, user.password))
            return user
        logger.info("Passwords do not match of user $username")
        throw UserNotFoundException(username)
    }

}