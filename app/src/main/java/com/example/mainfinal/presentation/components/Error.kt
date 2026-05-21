package com.example.mainfinal.presentation.components

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.runtime.Composable
import com.example.uikit.theme.TextBase

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Error(error: String?, onClear: () -> Unit) {

    if (error != null) {
        ModalBottomSheet(
            {
                onClear()
            }
        ) {
            TextBase(error)
        }
    }

}