//package com.zwolsman.kwetter.web.services
//
//import com.zwolsman.kwetter.web.resources.Kweet
//import org.springframework.cloud.openfeign.FeignClient
//import org.springframework.stereotype.Service
//import org.springframework.web.bind.annotation.PathVariable
//import org.springframework.web.bind.annotation.RequestMapping
//import org.springframework.web.bind.annotation.RequestMethod
//import org.springframework.web.bind.annotation.RequestParam
//import org.springframework.web.client.RestTemplate
//import org.springframework.web.util.DefaultUriBuilderFactory
//
//@FeignClient("kweet-service", url = "http://kweet-service")
//interface KweetClient {
//
//    @RequestMapping("{name}/kweets", method= [RequestMethod.GET])
//    fun findByUsername(@PathVariable name: String) : List<Kweet>
//
//    @RequestMapping("{id}", method = [RequestMethod.GET])
//    fun findById(@PathVariable id: String) : Kweet
//
//    @RequestMapping(method = [RequestMethod.POST])
//    fun createKweet(@RequestParam text: String, @RequestParam userId: String): Kweet
//}