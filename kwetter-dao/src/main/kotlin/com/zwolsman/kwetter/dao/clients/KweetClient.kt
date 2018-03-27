package com.zwolsman.kwetter.dao.clients

import com.zwolsman.kwetter.dao.encoders.PageableQueryEncoder
import com.zwolsman.kwetter.dao.models.Kweet
import feign.Logger
import org.springframework.beans.factory.ObjectFactory
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.web.bind.annotation.*
import org.springframework.boot.autoconfigure.http.HttpMessageConverters
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.cloud.openfeign.support.SpringEncoder


@FeignClient("kweet-service", url = "http://kweet-service", configuration = [KweetConfig::class])
interface KweetClient {

    @GetMapping("/{name}/kweets")
    fun findByUsername(@PathVariable name: String) : List<Kweet>

    @GetMapping("/{id}")
    fun findById(@PathVariable id: String) : Kweet

    @GetMapping("/{name}/timeline/")
    fun getTimeline(@PathVariable name: String, pageable: Pageable) : Page<Kweet>

    @PostMapping
    fun createKweet(@RequestParam text: String, @RequestParam userId: String): Kweet
}

@Configuration
class KweetConfig {
    @Bean
    fun feignLoggerLevel() = Logger.Level.FULL

    @Autowired
    private lateinit var messageConverters: ObjectFactory<HttpMessageConverters>

    @Bean
    fun feignEncoder() = PageableQueryEncoder(SpringEncoder(messageConverters))
}