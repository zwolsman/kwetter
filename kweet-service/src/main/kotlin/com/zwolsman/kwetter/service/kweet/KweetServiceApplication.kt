package com.zwolsman.kwetter.service.kweet

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class KweetServiceApplication {
    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            runApplication<KweetServiceApplication>(*args)
        }
    }
}