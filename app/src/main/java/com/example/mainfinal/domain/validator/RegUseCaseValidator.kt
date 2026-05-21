package com.example.mainfinal.domain.validator

import org.openapitools.client.models.RegisterRequest

object RegUseCaseValidator  {

     fun length(registerRequest: RegisterRequest): Boolean = true

     fun passwords(
        registerRequest: RegisterRequest,
        password2: String
    ): Boolean = true

     fun password(registerRequest: RegisterRequest): Boolean =true

     fun mail(registerRequest: RegisterRequest): Boolean =true

}