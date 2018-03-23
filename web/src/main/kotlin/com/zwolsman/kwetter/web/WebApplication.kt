package com.zwolsman.kwetter.web

import com.zwolsman.kwetter.dao.clients.KweetClient
import com.zwolsman.kwetter.dao.clients.UserClient
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.openfeign.EnableFeignClients


@SpringBootApplication()
@EnableFeignClients(basePackageClasses = [UserClient::class, KweetClient::class])
class WebApplication {
    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            runApplication<WebApplication>(*args)
        }
    }
}