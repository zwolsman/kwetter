package com.zwolsman.kwetter.web

import com.fasterxml.jackson.databind.annotation.JsonDeserialize
import com.zwolsman.kwetter.web.resources.SimplePageIml
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.domain.Page
import org.springframework.http.converter.HttpMessageConverter
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer


@JsonDeserialize(`as` = SimplePageIml::class)
interface PageMixIn

@Configuration
class WebConfig : WebMvcConfigurer {

    @Bean
    fun converter() =
        MappingJackson2HttpMessageConverter().apply {
            objectMapper.addMixIn(Page::class.java, PageMixIn::class.java)
        }


    override fun extendMessageConverters(converters: MutableList<HttpMessageConverter<*>>) {
        converters.add(converter())
        super.extendMessageConverters(converters)
    }

}