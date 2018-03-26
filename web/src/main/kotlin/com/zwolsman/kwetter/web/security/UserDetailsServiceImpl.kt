package com.zwolsman.kwetter.web.security

import com.zwolsman.kwetter.dao.clients.UserClient
import org.slf4j.LoggerFactory
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Service

@Service
class UserDetailsServiceImpl(private val userClient: UserClient, private val bCryptPasswordEncoder: BCryptPasswordEncoder) : UserDetailsService {

    override fun loadUserByUsername(username: String): UserDetails {
        val user = userClient.findByUsername(username)
        return User(user.username, user.password, emptyList())
    }

}