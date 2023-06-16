package com.private_projects.customkeyboard.ui

import android.content.Context
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.private_projects.customkeyboard.services.CustomKeyboardService

@Composable
fun RowScope.CustomKey( //Функция создания клавиши клавиатуры
    text: String = "",
    mainColor: Color,
    interactionSource: MutableInteractionSource,
    context: Context,
    viewModel: MainViewModel,
    isBlocked: Boolean = false,
    onKeyClick: () -> Unit
) {
    Text(
        text = text,
        color = mainColor,
        fontSize = 28.sp,
        modifier = Modifier
            .border(
                width = 1.dp,
                shape = RoundedCornerShape(5.dp),
                color = mainColor
            )
            .height(44.dp)
            .weight(1f)
            .clickable(
                interactionSource = interactionSource,
                indication = null,
                enabled = !isBlocked
            ) {
                onKeyClick()
                (context as CustomKeyboardService).currentInputConnection.commitText(
                    viewModel.text.value,
                    1
                )
            },
        textAlign = TextAlign.Center
    )
}