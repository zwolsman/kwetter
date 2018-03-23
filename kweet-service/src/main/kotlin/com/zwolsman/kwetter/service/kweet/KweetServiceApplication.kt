package com.zwolsman.kwetter.service.kweet

import com.zwolsman.kwetter.dao.clients.UserClient
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.openfeign.EnableFeignClients

@SpringBootApplication(scanBasePackageClasses = [UserClient::class])
@EnableFeignClients
class KweetServiceApplication {
    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            runApplication<KweetServiceApplication>(*args)
        }
    }
}