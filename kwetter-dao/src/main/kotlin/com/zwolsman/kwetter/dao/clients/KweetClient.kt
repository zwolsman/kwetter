package com.zwolsman.kwetter.dao.clients

import com.zwolsman.kwetter.dao.models.Kweet
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.web.bind.annotation.*

@FeignClient("kweet-service", url = "http://kweet-service")
interface KweetClient {

    @GetMapping("/{name}/kweets")
    fun findByUsername(@PathVariable name: String) : List<Kweet>

    @GetMapping("/{id}")
    fun findById(@PathVariable id: String) : Kweet

    @GetMapping("/{name}/timeline/")
    fun getTimelinePaged(@PathVariable name: String, pageable: Pageable) : Page<Kweet>

    @PostMapping
    fun createKweet(@RequestParam text: String, @RequestParam userId: String): Kweet
}