package com.example.mainfinal.domain.useCase

import com.example.mainfinal.domain.repository.AuthApiRep
import com.example.mainfinal.domain.validator.RegUseCaseValidator
import com.example.net.types.ResultType
import org.openapitools.client.models.RegisterRequest
import org.openapitools.client.models.RegisterUser200Response

object RegErrors{
    val email = "email invalid"
    val passwordShort = "password too short"
    val passwordMatch = "password didnt match"
    val passwordStrong = "password not strong"
}

class RegUseCase(
    val authApiRep: AuthApiRep,
    val validator: RegUseCaseValidator = RegUseCaseValidator
) {

    suspend fun registerUser(registerRequest: RegisterRequest, password2: String): ResultType<RegisterUser200Response> {

        if (validator.mailCheck(registerRequest)) {
            throw IllegalStateException(RegErrors.email)
        }

        if (validator.lengthPassword(registerRequest)) {
            throw IllegalStateException(RegErrors.passwordShort)
        }

        if (validator.passwordsMatch(registerRequest, password2)) {
            throw IllegalStateException(RegErrors.passwordMatch)
        }

        if (validator.passwordCheck(registerRequest)) {
            throw IllegalStateException(RegErrors.passwordStrong)
        }

        return authApiRep.registerUser(registerRequest)
    }

}

