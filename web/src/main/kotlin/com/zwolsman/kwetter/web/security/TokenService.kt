package com.zwolsman.kwetter.web.security

import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm
import com.zwolsman.kwetter.dao.clients.UserClient
import org.springframework.http.HttpHeaders
import org.springframework.security.core.Authentication
import org.springframework.security.web.authentication.AuthenticationSuccessHandler
import org.springframework.stereotype.Service
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse


@Service
class TokenService(private val userClient: UserClient) : AuthenticationSuccessHandler {
    companion object {
        private val algo = Algorithm.HMAC256("my-super-awesome-jwt-secret-for-jea-6")
        private const val PREFIX = "Bearer "
    }

    override fun onAuthenticationSuccess(request: HttpServletRequest, response: HttpServletResponse, authentication: Authentication) {
        val user = userClient.findByUsername(authentication.name)
        response.addHeader(HttpHeaders.AUTHORIZATION, PREFIX + JWT.create()
                .withClaim("username", user.username).sign(algo))
    }
}