package com.zwolsman.kwetter.service.account

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.netflix.eureka.EnableEurekaClient
import org.springframework.context.annotation.Bean
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder

@SpringBootApplication
@EnableEurekaClient
class AccountServiceApplication {
    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            runApplication<AccountServiceApplication>(*args)
        }
    }

    @Bean
    fun passwordEncoder() = BCryptPasswordEncoder()

}