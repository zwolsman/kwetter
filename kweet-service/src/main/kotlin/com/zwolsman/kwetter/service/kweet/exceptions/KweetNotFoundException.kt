package com.zwolsman.kwetter.service.kweet.exceptions

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ResponseStatus

@ResponseStatus(HttpStatus.NOT_FOUND)
class KweetNotFoundException(kweetId: String) : Exception("Kweet $kweetId not found")