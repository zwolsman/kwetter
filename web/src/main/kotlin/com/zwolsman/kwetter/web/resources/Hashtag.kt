package com.zwolsman.kwetter.web.resources

data class Hashtag(override val range: IntRange, val text: String) : KweetEntity