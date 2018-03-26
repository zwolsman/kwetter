package com.zwolsman.kwetter.web.security

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpMethod
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter
import org.springframework.web.cors.CorsConfiguration
import org.springframework.web.cors.CorsConfigurationSource
import org.springframework.web.cors.UrlBasedCorsConfigurationSource

@EnableWebSecurity
@Configuration
@EnableGlobalMethodSecurity
class Config(private val userDetailsService: UserDetailsServiceImpl, private val bCryptPasswordEncoder: BCryptPasswordEncoder, private val tokenService: TokenService) : WebSecurityConfigurerAdapter() {

    override fun configure(http: HttpSecurity) {
        http.cors().and().csrf().disable().authorizeRequests()
                .antMatchers(HttpMethod.POST, "/api/user/").permitAll()
                .anyRequest().authenticated()
                .and()
                .addFilterAfter(JWTAuthenticationFilter(authenticationManager(), tokenService), BasicAuthenticationFilter::class.java)
                //.addFilterAfter(JWTAuthorizationFilter(authenticationManager()), BasicAuthenticationFilter::class.java)
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and().headers().disable()
    }

    override fun configure(auth: AuthenticationManagerBuilder) {
        auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder)
    }


    @Bean
    fun corsConfigurationSource() : CorsConfigurationSource {
        val source = UrlBasedCorsConfigurationSource()
        source.registerCorsConfiguration("/**", CorsConfiguration().applyPermitDefaultValues())
        return source
    }
}