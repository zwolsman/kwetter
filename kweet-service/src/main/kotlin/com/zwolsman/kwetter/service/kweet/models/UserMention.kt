package com.zwolsman.kwetter.service.kweet.models

data class UserMention(override val range: IntRange, val name: String) : KweetEntity