package com.zwolsman.kwetter.web

import com.zwolsman.kwetter.dao.clients.KweetClient
import com.zwolsman.kwetter.dao.clients.UserClient
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.openfeign.EnableFeignClients
import org.springframework.context.annotation.Bean
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder


@SpringBootApplication()
@EnableFeignClients(basePackageClasses = [UserClient::class, KweetClient::class])
class WebApplication {
    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            runApplication<WebApplication>(*args)
        }
    }

    @Bean
    fun bCryptPasswordEncoder() = BCryptPasswordEncoder()
}