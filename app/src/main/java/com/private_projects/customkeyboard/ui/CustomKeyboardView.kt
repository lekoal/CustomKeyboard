package com.private_projects.customkeyboard.ui

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.AbstractComposeView

class CustomKeyboardView(context: Context) : AbstractComposeView(context) { //View для запуска и работы службы
    private var viewModel = MainViewModel()

    @Composable
    override fun Content() {
        CustomKeyboard(viewModel = viewModel)
    }
}