package com.example.mainfinal.presentation.common

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.compositionLocalOf
import androidx.navigation.NavController

val LocalNavController = compositionLocalOf<NavController> {
    error(" no nav init")
}