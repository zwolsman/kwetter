package com.zwolsman.kwetter.service.account.controllers

import com.zwolsman.kwetter.service.account.exceptions.UserNotFoundException
import com.zwolsman.kwetter.service.account.models.KwetterUser
import com.zwolsman.kwetter.service.account.repositories.UserRepository
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController

@RestController
class UserController(private val userRepository: UserRepository, private val bCryptPasswordEncoder: BCryptPasswordEncoder) {
    private val logger = LoggerFactory.getLogger(javaClass)

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun create(@RequestParam username: String, @RequestParam password: String): KwetterUser {
        val kwetterUser = KwetterUser(username, bCryptPasswordEncoder.encode(password))
        logger.info("Registering user ${kwetterUser.username}")
        return userRepository.save(kwetterUser)
    }

    @PostMapping("login")
    fun login(@RequestParam username: String, @RequestParam password: String): KwetterUser {
        val user = userRepository.findByUsername(username) ?: throw UserNotFoundException(username)

        if (bCryptPasswordEncoder.matches(password, user.password))
            return user
        throw UserNotFoundException(username)
    }

}