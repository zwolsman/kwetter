package com.zwolsman.kwetter.service.account.services

import com.zwolsman.kwetter.dao.exceptions.UserNotFoundException
import com.zwolsman.kwetter.dao.models.KwetterUser
import com.zwolsman.kwetter.service.account.repositories.UserRepository
import org.slf4j.LoggerFactory
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Service

@Service
class UserService(private val userRepository: UserRepository, private val bCryptPasswordEncoder: BCryptPasswordEncoder) {
    private val logger = LoggerFactory.getLogger(this::class.java)

    fun register(username: String, password: String): KwetterUser {
        val kwetterUser = KwetterUser(username, bCryptPasswordEncoder.encode(password))
        logger.info("Registering user ${kwetterUser.username}")
        return userRepository.save(kwetterUser)
    }

    fun findByUsername(name: String): KwetterUser {
        return userRepository.findByUsername(name) ?: throw UserNotFoundException(name)
    }
}