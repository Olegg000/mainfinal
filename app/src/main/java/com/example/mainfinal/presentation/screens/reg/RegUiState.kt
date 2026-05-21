package com.example.mainfinal.presentation.screens.reg

import org.openapitools.client.models.RegisterRequest

data class RegUiState (
    val password2: String = "",
    val isChecked: Boolean = false,
    val firstName: String = "",
    val lastName: String = "",
    val email: String = "",
    val password: String = "",
)