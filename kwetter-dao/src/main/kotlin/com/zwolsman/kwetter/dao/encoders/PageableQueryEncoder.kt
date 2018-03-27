package com.zwolsman.kwetter.dao.encoders

import feign.RequestTemplate
import feign.codec.Encoder
import org.springframework.data.domain.Pageable
import java.lang.reflect.Type

class PageableQueryEncoder(private val delegate: Encoder): Encoder {
    override fun encode(`object`: Any, bodyType: Type, template: RequestTemplate) {

        if(`object` is Pageable) {
            print("pageable encoding thingy!")
            template.query("page", `object`.pageNumber.toString())
            template.query("size", `object`.pageSize.toString())
            if(`object`.sort.isSorted)
                TODO("Make sort support")
        } else {
            delegate.encode(`object`, bodyType, template)
        }
    }

}