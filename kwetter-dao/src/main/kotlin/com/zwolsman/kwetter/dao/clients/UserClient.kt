package com.zwolsman.kwetter.dao.clients

import com.zwolsman.kwetter.dao.models.KwetterUser
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.*

@FeignClient("account-service", url = "http://account-service")
interface UserClient {

    @GetMapping("{name}")
    fun findByUsername(@PathVariable name: String) : KwetterUser

    @PostMapping
    fun register(@RequestParam username: String, @RequestParam password: String) : KwetterUser
}