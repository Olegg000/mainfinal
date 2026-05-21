package com.example.mainfinal.presentation.screens.reg

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.AbsoluteRoundedCornerShape
import androidx.compose.material3.Checkbox
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.mainfinal.presentation.screens.base.ScreenWrapper
import com.example.uikit.components.BaseButton
import com.example.uikit.components.ButtonsStyle
import com.example.uikit.components.CardBase
import com.example.uikit.components.Check
import com.example.uikit.components.InputsDef
import com.example.uikit.components.PasswordInput
import com.example.uikit.theme.GlobalTextStyle
import com.example.uikit.theme.Primary
import com.example.uikit.theme.Secondary
import com.example.uikit.theme.Spaces.s16
import com.example.uikit.theme.Spaces.s24
import com.example.uikit.theme.Spaces.s8
import com.example.uikit.theme.TextBase

@Composable
fun RegScreen(
    viewModel: RegViewModel = viewModel()
) {

    ScreenWrapper(viewModel, "RegScreen") { state, controller, logger ->

        LazyColumn(Modifier.fillMaxSize()) {
            item {
                Column(
                    Modifier
                        .fillMaxSize()
                        .padding(top = 86.dp)
                        .padding(horizontal = 20.dp)
                ) {
                    CardBase(
                        shape = AbsoluteRoundedCornerShape(12.dp),
                        offsetDp = 4.dp,
                        blur = 12.dp,
                    ) {
                        Column(Modifier.padding(s24)) {

                            Column(
                                Modifier.fillMaxWidth(),
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                TextBase(
                                    "HR Connect",
                                    color = Primary,
                                    style = GlobalTextStyle.ScreenHeader
                                )
                                Spacer(Modifier.height(s8))
                                TextBase(
                                    "Modern Talent Engine for HR Excellence",
                                    color = Secondary,
                                )
                                Spacer(Modifier.height(s24))
                                Row(
                                    Modifier.fillMaxWidth(),
                                    verticalAlignment = Alignment.CenterVertically,
                                    horizontalArrangement = Arrangement.Center
                                ) {
                                    InputsDef(
                                        state.firstName,
                                        {
                                            viewModel.firstName(it)
                                        },
                                        label = "First Name",
                                        placeholder = "John",
                                        modifier = Modifier.weight(1f),
                                        inputModifier = Modifier.testTag("fn")
                                    )
                                    Spacer(Modifier.width(s16))
                                    InputsDef(
                                        state.lastName,
                                        {
                                            viewModel.lastName(it)
                                        },
                                        label = "Last Name",
                                        placeholder = "Doe",
                                        modifier = Modifier.weight(1f),
                                        inputModifier = Modifier.testTag("ln")
                                    )
                                }
                                Spacer(Modifier.height(s16))
                                InputsDef(
                                    state.email,
                                    {
                                        viewModel.email(it)
                                    },
                                    label = "Email",
                                    placeholder = "name@domain.ru",
                                    modifier = Modifier.fillMaxWidth(),
                                    inputModifier = Modifier.testTag("e")
                                )
                                Spacer(Modifier.height(s16))
                                PasswordInput(
                                    state.password,
                                    {
                                        viewModel.password(it)
                                    },
                                    label = "Password",
                                    placeholder = "••••••••",
                                    modifier = Modifier.fillMaxWidth(),
                                    inputModifier = Modifier.testTag("p")
                                )
                                Spacer(Modifier.height(s16))
                                PasswordInput(
                                    state.password2,
                                    {
                                        viewModel.password2(it)
                                    },
                                    label = "Confirm Password",
                                    placeholder = "••••••••",
                                    modifier = Modifier.fillMaxWidth(),
                                    inputModifier = Modifier.testTag("p2")
                                )
                                Spacer(Modifier.height(20.dp))
                                Check(
                                    "I agree to the terms of use and privacy",
                                    state.isChecked,
                                    {
                                        viewModel.checked(it)
                                    },
                                )
                                Spacer(Modifier.height(32.dp))
                                BaseButton(
                                    "Register",
                                    {
                                        viewModel.reg()
                                    },
                                    ButtonsStyle.Primary,
                                    modifier = Modifier.fillMaxWidth().testTag("b")
                                )
                                Spacer(Modifier.height(40.dp))
                                Row(
                                    horizontalArrangement = Arrangement.Center
                                ) {
                                    TextBase(
                                        "Already have an account? "
                                    )
                                    TextBase(
                                        "Sign in",
                                        color = Primary,
                                        modifier = Modifier.clickable {
                                            controller.navigate("login")
                                        })
                                }
                            }

                        }
                    }
                }
            }
        }

    }

}