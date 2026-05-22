package com.example.mainfinal.presentation.screens.chat

import com.example.mainfinal.presentation.screens.base.BaseVIewModel
import com.example.net.common.ContextHolder
import com.example.net.types.ResultType
import com.google.mediapipe.tasks.genai.llminference.LlmInference

class ChatViewModel(

): BaseVIewModel<ChatUiState>(ChatUiState()) {

    override fun init() {
        launchWithStatus(
            {
                val opt = LlmInference.LlmInferenceOptions.builder()
                    .setModelPath("/data/data/com.example.mainfinal/files/qwen.task")
                    .build()
                val inst = LlmInference.createFromOptions(ContextHolder.getContext(),opt)

                ResultType.Success(inst)
            },
            {
                copy(llm = it)
            }
        )
    }

    val msg = field<String> { copy(message = it) }

    fun sentMessage() {

        update { it.copy(history = state.value.history + "Вы: ${state.value.message}") }

        launchWithStatus(
            {
                val resp = state.value.llm?.generateResponse(state.value.message) ?: "Ошибка генерации"
                ResultType.Success(resp)
            },
            {
                copy(
                    history = history + "ИИ: $it",
                    message = ""
                )
            }
        )
    }

}