package com.example.mainfinal.presentation.screens.chat

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.mainfinal.R
import com.example.mainfinal.presentation.screens.base.ScreenWrapper
import com.example.uikit.components.InputsDef
import com.example.uikit.theme.TextBase

@Composable
fun ChatScreen(
    viewModel: ChatViewModel = viewModel()
) {

    ScreenWrapper(viewModel, "ChatScreen") { state, controller, logger ->


                Column(Modifier.fillMaxSize().padding(horizontal = 20.dp)) {
                    LazyColumn (Modifier.fillMaxSize().weight(1f)) {
                        items(state.history) {
                            Text(
                                it
                            )
                        }
                    }
                    InputsDef(
                        state.message,
                        {
                            viewModel.msg(it)
                        },
                        placeholder = "Спросить ИИ...",
                        trailingIcon = {
                            IconButton({
                                viewModel.sentMessage()
                            }) {
                                Icon(
                                    painterResource(com.example.uikit.R.drawable.eye),
                                    contentDescription = null
                                )
                            }
                        },
                        modifier = Modifier.padding(bottom = 50.dp)
                    )
                }
            }
        }