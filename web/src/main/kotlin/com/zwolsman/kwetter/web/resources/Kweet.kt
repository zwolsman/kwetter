package com.zwolsman.kwetter.web.resources

import org.springframework.hateoas.ResourceSupport
import java.util.*

open class Kweet(val text: String, val createdAt: Date = Date(), val entities: Map<String, List<KweetEntity>>, val user: KwetterUser) : ResourceSupport() {

}