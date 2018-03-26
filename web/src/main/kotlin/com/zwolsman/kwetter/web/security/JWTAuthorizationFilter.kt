package com.zwolsman.kwetter.web.security

import com.auth0.jwt.JWT
import org.springframework.http.HttpHeaders
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.BadCredentialsException
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter
import javax.servlet.FilterChain
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

class JWTAuthorizationFilter(authenticationManager: AuthenticationManager) : BasicAuthenticationFilter(authenticationManager) {

    override fun doFilterInternal(request: HttpServletRequest, response: HttpServletResponse, chain: FilterChain) {
        val header:String? = request.getHeader(HttpHeaders.AUTHORIZATION)

        if(header == null) {
            super.doFilterInternal(request, response, chain)
            return
        }

        val token = header.substringAfter("Bearer ")
        if(token.isBlank())
            throw BadCredentialsException("Failed to get the token")

        try {
            val user = JWT.decode(token)
            SecurityContextHolder.getContext().authentication = UsernamePasswordAuthenticationToken(user.subject, null, emptyList())
            chain.doFilter(request, response)
        } catch (_: Exception) {
            throw BadCredentialsException("Failed to decode jwt authentication token")
        }

    }
}