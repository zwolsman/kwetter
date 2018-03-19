package com.zwolsman.kwetter.web

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.client.loadbalancer.LoadBalanced
import org.springframework.context.annotation.Bean
import org.springframework.web.client.RestTemplate

@SpringBootApplication
class WebApplication {
    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            runApplication<WebApplication>(*args)
        }
    }

    @Bean
    @LoadBalanced
    fun restTemplate() = RestTemplate()
}