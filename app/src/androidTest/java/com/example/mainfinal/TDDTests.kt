package com.example.mainfinal

import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.test.ExperimentalTestApi
import androidx.compose.ui.test.hasText
import androidx.compose.ui.test.junit4.v2.createAndroidComposeRule
import androidx.compose.ui.test.junit4.v2.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextInput
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.example.mainfinal.domain.validator.RegUseCaseValidator
import com.example.mainfinal.presentation.common.LocalNavController
import com.example.mainfinal.presentation.screens.reg.RegScreen
import com.example.net.common.ContextHolder
import com.example.uikit.theme.TextBase
import kotlinx.coroutines.delay
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.openapitools.client.models.RegisterRequest

@RunWith(AndroidJUnit4::class)
class TDDTests {

    private val cE = "name@domain.ru"
    private val cP = "P@ssw0rd"
    private val ucPsh = "pass"
    private val ucP = "password"
    private val ucP2 = "pass2"
    private val ucE = "MAIL!!!"

    private fun rE(): String {
        return "test${System.currentTimeMillis()}@test.test"
    }

    @Test
    fun `Валидация Email`() {
        val res = RegUseCaseValidator.mail(
            RegisterRequest(
                firstName = "",
                lastName = "",
                email = cE,
                password = "",
                role = ""
            )
        )
        assert(!res)
    }

    @Test
    fun `Валидация пароля`() {
        val res = RegUseCaseValidator.password(
            RegisterRequest(
                firstName = "",
                lastName = "",
                email = cP,
                password = "",
                role = ""
            )
        )
        assert(!res)
    }

    @Test
    fun `Валидация подтверждения пароля`() {
        val res =RegUseCaseValidator.passwords(
            RegisterRequest(
                firstName = "",
                lastName = "",
                email = cP,
                password = "",
                role = ""
            ),
            cP
        )
        assert(!res)
    }

    @get:Rule
    val rule = createAndroidComposeRule<MainActivity>()

    @Test
    fun `Вызов сообщения об ошибке при некорректном Email`() {
        rule.onNodeWithTag("e").performTextInput(ucE)
        rule.onNodeWithTag("b").performClick()
        rule.onNodeWithText("email invalid").assertExists()
    }

    @Test
    fun `Вызов сообщения об ошибке при некорректном пароле`() {
        rule.onNodeWithTag("e").performTextInput(cE)
        rule.onNodeWithTag("p").performTextInput(ucPsh)
        rule.onNodeWithTag("b").performClick()
        rule.onNodeWithText("password too short").assertExists()
    }

    @OptIn(ExperimentalTestApi::class)
    @Test
    fun `Успешная регистрация`() {
        rule.onNodeWithTag("e").performTextInput(rE())
        rule.onNodeWithTag("ln").performTextInput(cE)
        rule.onNodeWithTag("fn").performTextInput(cE)
        rule.onNodeWithTag("p").performTextInput(cP)
        rule.onNodeWithTag("p2").performTextInput(cP)
        rule.onNodeWithTag("b").performClick()
        rule.waitUntilExactlyOneExists(hasText("login"))
    }


    @OptIn(ExperimentalTestApi::class)
    @Test
    fun `Провальная  регистрация`() {
        rule.onNodeWithTag("e").performTextInput(cE)
        rule.onNodeWithTag("ln").performTextInput(cE)
        rule.onNodeWithTag("fn").performTextInput(cE)
        rule.onNodeWithTag("p").performTextInput(cP)
        rule.onNodeWithTag("p2").performTextInput(cP)
        rule.onNodeWithTag("b").performClick()
        rule.waitUntilExactlyOneExists(hasText("409", substring = true))
    }

}