package com.zwolsman.kwetter.service.account.exceptions

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ResponseStatus

@ResponseStatus(HttpStatus.NOT_FOUND)
class UserNotFoundException(username: String) : Exception("User $username not found.")