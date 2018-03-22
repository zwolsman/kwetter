package com.zwolsman.kwetter.service.kweet

import com.zwolsman.kwetter.service.kweet.serializers.ObjectIdDeserializer
import com.zwolsman.kwetter.service.kweet.serializers.ObjectIdSerializer
import org.bson.types.ObjectId
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.converter.HttpMessageConverter
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer

@Configuration
class WebConfig : WebMvcConfigurer {

    override fun configureMessageConverters(converters: MutableList<HttpMessageConverter<*>>) {
        converters.add(jacksonConverter())
    }

    @Bean
    fun jacksonConverter(): MappingJackson2HttpMessageConverter {
        val builder = Jackson2ObjectMapperBuilder()
        builder.serializerByType(ObjectId::class.java, ObjectIdSerializer())

        builder.deserializerByType(ObjectId::class.java, ObjectIdDeserializer())
        return MappingJackson2HttpMessageConverter(builder.build())
    }
}