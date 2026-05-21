package com.example.mainfinal.domain.validator

import org.openapitools.client.models.RegisterRequest

object RegUseCaseValidator {

    fun lengthPassword(registerRequest: RegisterRequest): Boolean = registerRequest.password.length < 8

    fun passwordsMatch(
        registerRequest: RegisterRequest,
        password2: String
    ): Boolean = registerRequest.password != password2

    fun passwordCheck(registerRequest: RegisterRequest): Boolean =
        (!registerRequest.password.any { it.isLowerCase() } || !registerRequest.password.any { it.isUpperCase() }
                || !registerRequest.password.any { it.isDigit() } || !registerRequest.password.any { !it.isLetterOrDigit() })

    fun mailCheck(registerRequest: RegisterRequest): Boolean =
        !registerRequest.email.all { it.isLowerCase() || it.isDigit() || it == '@' || it == '.' }

}