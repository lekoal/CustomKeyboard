package com.private_projects.customkeyboard.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.private_projects.customkeyboard.data.KeyboardKeys

@Composable
fun CustomKeyboard(
    modifier: Modifier = Modifier,
    viewModel: MainViewModel,
    isBlocked: Boolean = false
) { //Создаём клавиатуру с помощью столбцов и строк
    val currentLocale = viewModel.currentLocale
    val isCaps = viewModel.isCaps
    Card(
        modifier = modifier
            .padding(10.dp),
        shape = RoundedCornerShape(10.dp)
    ) {
        Column(
            modifier = modifier
                .fillMaxWidth()
                .padding(10.dp)
        ) {
            CustomRow(isDigitRow = true, viewModel = viewModel, isBlocked = isBlocked)
            CustomRow(
                keys = if (currentLocale.value == BACKSPACE_ENG_TEXT
                    || currentLocale.value == BACKSPACE_CAPS_ENG_TEXT
                ) {
                    if (isCaps.value) {
                        KeyboardKeys.EngCaps.firstTextBlock
                    } else {
                        KeyboardKeys.Eng.firstTextBlock
                    }
                } else {
                    if (isCaps.value) {
                        KeyboardKeys.RusCaps.firstTextBlock
                    } else {
                        KeyboardKeys.Rus.firstTextBlock
                    }
                }, viewModel = viewModel,
                isBlocked = isBlocked
            )
            CustomRow(
                keys = if (currentLocale.value == BACKSPACE_ENG_TEXT
                    || currentLocale.value == BACKSPACE_CAPS_ENG_TEXT
                ) {
                    if (isCaps.value) {
                        KeyboardKeys.EngCaps.secondTextBlock
                    } else {
                        KeyboardKeys.Eng.secondTextBlock
                    }
                } else {
                    if (isCaps.value) {
                        KeyboardKeys.RusCaps.secondTextBlock
                    } else {
                        KeyboardKeys.Rus.secondTextBlock
                    }
                }, viewModel = viewModel,
                isBlocked = isBlocked
            )
            CustomRow(
                keys = if (currentLocale.value == BACKSPACE_ENG_TEXT
                    || currentLocale.value == BACKSPACE_CAPS_ENG_TEXT
                ) {
                    if (isCaps.value) {
                        KeyboardKeys.EngCaps.thirdTextBlock
                    } else {
                        KeyboardKeys.Eng.thirdTextBlock
                    }
                } else {
                    if (isCaps.value) {
                        KeyboardKeys.RusCaps.thirdTextBlock
                    } else {
                        KeyboardKeys.Rus.thirdTextBlock
                    }
                }, viewModel = viewModel,
                isBlocked = isBlocked
            )
            CustomRow(isFunctionalRow = true, viewModel = viewModel, isBlocked = isBlocked)
        }
    }
}