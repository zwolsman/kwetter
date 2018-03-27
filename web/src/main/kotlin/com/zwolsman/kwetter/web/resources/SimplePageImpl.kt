package com.zwolsman.kwetter.web.resources

import com.fasterxml.jackson.annotation.JsonProperty
import org.springframework.data.domain.*
import java.util.function.Function
import com.fasterxml.jackson.databind.annotation.JsonDeserialize

class SimplePageIml<T>(@JsonProperty("content") content: List<T>, @JsonProperty("page") number: Int, @JsonProperty size: Int, @JsonProperty totalElements: Long) : Page<T> {
    private val delegate = PageImpl(content, PageRequest.of(number, size), totalElements)

    override fun hasPrevious() = delegate.hasPrevious()
    override fun getSize() = delegate.size
    override fun getSort() = delegate.sort
    override fun previousPageable() = delegate.previousPageable()
    override fun getNumberOfElements() = delegate.numberOfElements
    override fun getContent() = delegate.content
    override fun iterator() = delegate.iterator()
    override fun <U : Any?> map(converter: Function<in T, out U>) = delegate.map(converter)
    override fun nextPageable() = delegate.nextPageable()
    override fun hasNext() = delegate.hasNext()
    override fun isFirst() = delegate.isFirst
    override fun hasContent() = delegate.hasContent()
    override fun getTotalPages() = delegate.totalPages
    override fun isLast() = delegate.isLast
    override fun getTotalElements() = delegate.totalElements
    override fun getNumber() = delegate.number

}