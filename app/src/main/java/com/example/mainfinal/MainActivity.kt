package com.example.mainfinal

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.CompositionLocalProvider
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.mainfinal.presentation.common.LocalNavController
import com.example.mainfinal.presentation.screens.reg.RegScreen
import com.example.net.common.ContextHolder
import com.example.uikit.theme.TextBase

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ContextHolder.init(this.applicationContext)
        enableEdgeToEdge()
        setContent {

            val nav = rememberNavController()

            CompositionLocalProvider(
                LocalNavController provides nav,
            ) {
                NavHost(
                    startDestination = "reg",
                    navController = nav
                ) {

                    composable("reg") {
                        RegScreen()
                    }

                    composable("login") {
                        TextBase("login")
                    }

                }
            }

        }
    }
}