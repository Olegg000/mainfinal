package com.example.mainfinal.presentation.screens.chat

import com.google.mediapipe.tasks.genai.llminference.LlmInference

data class ChatUiState (
    val message: String = "",
    val history: List<String> = emptyList(),
    val llm: LlmInference? = null,
)