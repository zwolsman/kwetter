//package com.zwolsman.kwetter.web.services
//
//import com.zwolsman.kwetter.web.resources.KwetterUser
//import org.springframework.cloud.openfeign.FeignClient
//import org.springframework.web.bind.annotation.PathVariable
//import org.springframework.web.bind.annotation.RequestMapping
//import org.springframework.web.bind.annotation.RequestMethod
//
//
//@FeignClient("account-service", url = "http://account-service")
//interface UserClient {
//
//    @RequestMapping("{name}", method= [(RequestMethod.GET)])
//    fun findByUsername(@PathVariable name: String) : KwetterUser
//}