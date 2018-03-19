package com.zwolsman.kwetter.service.kweet.models

data class Hashtag(override val range: IntRange, val text: String) : KweetEntity