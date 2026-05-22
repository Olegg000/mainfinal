package com.example.mainfinal.presentation.screens.base

import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavController
import com.example.mainfinal.common.AppLogger
import com.example.mainfinal.presentation.common.LocalNavController
import com.example.mainfinal.presentation.components.Error
import com.example.mainfinal.presentation.components.Loading

@Composable
fun <T>ScreenWrapper(
    baseVIewModel: BaseVIewModel<T>,
    screenName: String,
    content: @Composable (T, NavController, AppLogger) -> Unit,
) {
    val state by baseVIewModel.state.collectAsState()
    val error by baseVIewModel.error.collectAsState()
    val isLoading by baseVIewModel.isLoading.collectAsState()
    val nav = LocalNavController.current

    val logger = remember { AppLogger(screenName) }

    Error(error,{baseVIewModel.clearError()})
    Loading(isLoading)

    DisposableEffect(Unit) {
        logger.i("Screen","Загрузился")
        onDispose {
            logger.i("Screen","Выгрузился")
        }
    }

    LaunchedEffect(Unit) {
        baseVIewModel.init()
        baseVIewModel.nav.collect { nav.navigate(it) }
    }


    content(state,nav,logger)

}