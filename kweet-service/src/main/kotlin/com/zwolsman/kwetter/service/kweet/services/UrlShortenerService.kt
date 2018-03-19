package com.zwolsman.kwetter.service.kweet.services

import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport
import com.google.api.client.http.HttpRequestInitializer
import com.google.api.client.json.jackson2.JacksonFactory
import com.google.api.services.urlshortener.Urlshortener
import com.google.api.services.urlshortener.model.Url
import org.springframework.stereotype.Service

@Service
class UrlShortenerService {
    companion object {
        private const val API_KEY = "AIzaSyBfcJu8tNHn2BJrQuM1s0gOAJ4NZsoPykQ"
        val shortener = Urlshortener.Builder(GoogleNetHttpTransport.newTrustedTransport(), JacksonFactory(), HttpRequestInitializer {
            it.setInterceptor { request ->
                request.url.put("key", API_KEY)
            }
        })
                .setApplicationName("kwetter").build()

    }

    fun getShortUrl(url: String) =
            shortener.url().insert(Url().setLongUrl(url)).execute().id

}