package com.example.mainfinal.domain.useCase

import com.example.mainfinal.domain.repository.AuthApiRep
import com.example.mainfinal.domain.validator.RegUseCaseValidator
import com.example.net.types.ResultType
import org.openapitools.client.models.RegisterRequest
import org.openapitools.client.models.RegisterUser200Response

class RegUseCase(
    val authApiRep: AuthApiRep,
    val r: RegUseCaseValidator = RegUseCaseValidator
) {

    suspend fun registerUser(registerRequest: RegisterRequest, password2: String): ResultType<RegisterUser200Response> {



        if (r.passwords(registerRequest, password2)) {
            throw IllegalStateException("password didnt match")
        }

        if (r.password(registerRequest)) {
            throw IllegalStateException("password not strong")
        }

        return authApiRep.registerUser(registerRequest)
    }

}

