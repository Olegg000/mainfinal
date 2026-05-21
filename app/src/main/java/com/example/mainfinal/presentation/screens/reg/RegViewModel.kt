package com.example.mainfinal.presentation.screens.reg

import com.example.mainfinal.data.remote.UserApi
import com.example.mainfinal.domain.repository.AuthApiRep
import com.example.mainfinal.domain.useCase.RegUseCase
import com.example.mainfinal.presentation.screens.base.BaseVIewModel
import org.openapitools.client.models.RegisterRequest

class RegViewModel(
    val authApiRep: AuthApiRep = UserApi(),
    val regUseCase: RegUseCase = RegUseCase(authApiRep)
): BaseVIewModel<RegUiState>(RegUiState()) {

    val password2 = field<String> { copy(password2 = it) }
    val firstName = field<String> { copy(firstName = it) }
    val lastName = field<String> { copy(lastName = it) }
    val email = field<String> { copy(email = it) }
    val password = field<String> { copy(password = it) }
    val checked = field<Boolean> { copy(isChecked =it) }

    fun reg () {

        launchWithStatus(
            {
                regUseCase.registerUser(
                    RegisterRequest(
                        firstName = state.value.firstName,
                        lastName = state.value.lastName,
                        email = state.value.email,
                        password = state.value.password,
                        role = ""
                    ),
                    password2 = state.value.password2
                )
            },
            {
                navigate("login")
                copy()
            }
        )
    }

}