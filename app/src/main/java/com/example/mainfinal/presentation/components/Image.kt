package com.example.mainfinal.presentation.components

import android.graphics.Bitmap
import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.result.launch
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.example.uikit.components.Avatar
import com.example.uikit.theme.TextBase

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Image() {

    var isShowModal by remember { mutableStateOf(false) }
    var imageBit by remember { mutableStateOf<Bitmap?>(null) }
    var imageUri by remember { mutableStateOf<Uri?>(null) }

    val galL = rememberLauncherForActivityResult(ActivityResultContracts.GetContent()) { uri ->
        uri.let {
            imageUri = uri
            imageBit = null
            isShowModal = false
        }
    }

    val camL = rememberLauncherForActivityResult(ActivityResultContracts.TakePicturePreview()) { b ->
        b.let {
            imageBit = b
            imageUri = null
            isShowModal = false
        }
    }

    if (isShowModal) {
        ModalBottomSheet(
            {isShowModal = false
            }
        ) {
            Column() {
                TextButton(
                    {
                        galL.launch("image/*")
                    }
                ) {
                    TextBase("gal")
                }
                TextButton(
                    {
                        camL.launch()
                    }
                ) {
                    TextBase("cam")
                }
            }

        }
    }

    Avatar(
        imageUri.toString(),
        "",
        "",
        modifier = Modifier.clickable{
            isShowModal = true
        },
    )
}