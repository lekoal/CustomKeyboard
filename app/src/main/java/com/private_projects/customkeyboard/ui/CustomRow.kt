package com.private_projects.customkeyboard.ui

import android.content.Context
import android.view.inputmethod.ExtractedTextRequest
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Divider
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.PopupProperties
import com.private_projects.customkeyboard.R
import com.private_projects.customkeyboard.data.KeyboardKeys
import com.private_projects.customkeyboard.data.MainColors
import com.private_projects.customkeyboard.services.CustomKeyboardService
import com.private_projects.customkeyboard.ui.theme.Blue800
import com.private_projects.customkeyboard.ui.theme.Brown700
import com.private_projects.customkeyboard.ui.theme.Orange900
import com.private_projects.customkeyboard.ui.theme.Teal900

const val BACKSPACE_CAPS_RUS_TEXT = "ЙЦУКЕН"
const val BACKSPACE_RUS_TEXT = "йцукен"
const val BACKSPACE_CAPS_ENG_TEXT = "QWERTY"
const val BACKSPACE_ENG_TEXT = "qwerty"

@Composable
fun CustomRow(
    modifier: Modifier = Modifier,
    keys: List<String> = emptyList(),
    isDigitRow: Boolean = false,
    isFunctionalRow: Boolean = false,
    isBlocked: Boolean,
    viewModel: MainViewModel
) { //Строки для клавиатуры в зависимости от типа вводимых данных
    var expandColorMenu by remember {
        mutableStateOf(false)
    }
    val interactionSource = remember {
        MutableInteractionSource()
    }
    val context = LocalContext.current

    //SharedPreferences для сохранения выбранного цвета
    val preferences =
        LocalContext.current.getSharedPreferences("mainColor", Context.MODE_PRIVATE)
    if (!preferences.contains("currentColor")) {
        preferences
            .edit()
            .putString("currentColor", MainColors.TEAL)
            .apply()
    } else {
        viewModel.setColor(preferences.getString("currentColor", MainColors.TEAL)!!)
    }
    val mainColor = rememberSaveable {
        viewModel.mainColor
    }
    val currentLocale = viewModel.currentLocale
    val isThirdTextBlock =
        keys == KeyboardKeys.Eng.thirdTextBlock ||
                keys == KeyboardKeys.Rus.thirdTextBlock ||
                keys == KeyboardKeys.EngCaps.thirdTextBlock ||
                keys == KeyboardKeys.RusCaps.thirdTextBlock
    val funcKeyModifier = Modifier
        .border(
            width = 1.dp,
            shape = RoundedCornerShape(5.dp),
            color = getMainColor(mainColor.value)
        )
        .height(44.dp)
        .padding(5.dp)
    if (isDigitRow) {
        Row(
            modifier = modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            for (i in 1..9) {
                CustomKey(
                    text = i.toString(),
                    mainColor = getMainColor(mainColor.value),
                    viewModel = viewModel,
                    interactionSource = interactionSource,
                    context = context,
                    isBlocked = isBlocked
                ) {
                    viewModel.onDigitKeyPressed(i)
                }
            }
            CustomKey(
                text = "0", mainColor = getMainColor(mainColor.value), viewModel = viewModel,
                interactionSource = interactionSource,
                context = context,
                isBlocked = isBlocked
            ) {
                viewModel.onDigitKeyPressed(0)
            }
        }
    }
    if (keys.isNotEmpty()) {
        if (isThirdTextBlock) {
            Row(modifier = modifier.fillMaxWidth()) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_baseline_keyboard_capslock_24),
                    contentDescription = stringResource(
                        R.string.capslock_button_desc
                    ),
                    tint = getMainColor(mainColor.value),
                    modifier = funcKeyModifier
                        .weight(1.2f)
                        .clickable {
                            viewModel.onCapsKeyPressed()
                        }
                )
                keys.forEach { key ->
                    CustomKey(
                        text = key,
                        mainColor = getMainColor(mainColor.value),
                        viewModel = viewModel,
                        interactionSource = interactionSource,
                        context = context,
                        isBlocked = isBlocked
                    ) {
                        viewModel.onTextKeyPressed(key)
                    }
                }
                Icon(
                    painter = painterResource(id = R.drawable.ic_baseline_backspace_24),
                    contentDescription = stringResource(
                        R.string.backspace_button_desc
                    ),
                    tint = getMainColor(mainColor.value),
                    modifier = funcKeyModifier
                        .weight(1.2f)
                        .clickable(
                            interactionSource = interactionSource,
                            indication = null,
                            enabled = !isBlocked
                        ) {
                            val connection =
                                (context as CustomKeyboardService).currentInputConnection
                            connection.deleteSurroundingText(1, 0)
                            if (connection.getExtractedText(
                                    ExtractedTextRequest(),
                                    0
                                ).text.isEmpty() && !viewModel.isCaps.value
                            ) {
                                viewModel.onCapsKeyPressed()
                            }
                        }
                )
            }
        } else {
            Row(modifier = modifier.fillMaxWidth()) {
                keys.forEach { key ->
                    CustomKey(
                        text = key,
                        mainColor = getMainColor(mainColor.value),
                        viewModel = viewModel,
                        interactionSource = interactionSource,
                        context = context,
                        isBlocked = isBlocked
                    ) {
                        viewModel.onTextKeyPressed(key)
                    }
                }
            }
        }
    }

    if (isFunctionalRow) {
        Row(modifier = modifier.fillMaxWidth()) {
            Icon(
                painter = painterResource(id = R.drawable.ic_baseline_language_24),
                contentDescription = stringResource(
                    R.string.change_language_button_desc
                ),
                tint = getMainColor(mainColor.value),
                modifier = funcKeyModifier
                    .weight(1f)
                    .clickable {
                        viewModel.changeLocale()
                    }
            )
            Text(
                text = ",",
                color = getMainColor(mainColor.value),
                fontSize = 28.sp,
                textAlign = TextAlign.Center,
                modifier = funcKeyModifier
                    .weight(1f)
                    .clickable(
                        interactionSource = interactionSource,
                        indication = null,
                        enabled = !isBlocked
                    ) {
                        (context as CustomKeyboardService).currentInputConnection.commitText(
                            ", ",
                            2
                        )
                    }
            )
            Text(
                text = currentLocale.value,
                color = getMainColor(mainColor.value),
                fontSize = 28.sp,
                textAlign = TextAlign.Center,
                modifier = funcKeyModifier
                    .weight(5f)
                    .clickable(
                        interactionSource = interactionSource,
                        indication = null,
                        enabled = !isBlocked
                    ) {
                        (context as CustomKeyboardService).currentInputConnection.commitText(
                            " ",
                            1
                        )
                    }
            )
            Text(
                text = ".",
                color = getMainColor(mainColor.value),
                fontSize = 28.sp,
                textAlign = TextAlign.Center,
                modifier = funcKeyModifier
                    .weight(1f)
                    .clickable(
                        interactionSource = interactionSource,
                        indication = null,
                        enabled = !isBlocked
                    ) {
                        (context as CustomKeyboardService).currentInputConnection.commitText(
                            ". ",
                            2
                        )
                        viewModel.onCapsKeyPressed()
                    }
            )
            Box(
                modifier = funcKeyModifier
                    .weight(1f),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_baseline_palette_24),
                    contentDescription = stringResource(
                        R.string.change_color_key_desc
                    ),
                    tint = getMainColor(mainColor.value),
                    modifier = Modifier
                        .clickable {
                            expandColorMenu = true
                        }
                )
                DropdownMenu(
                    expanded = expandColorMenu,
                    onDismissRequest = { expandColorMenu = false },
                    properties = PopupProperties(focusable = false),
                    offset = DpOffset((-5).dp, (-200).dp),
                    modifier = Modifier.width(48.dp)
                ) {
                    DropdownMenuItem(text = {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_baseline_palette_24),
                            contentDescription = null,
                            tint = getMainColor(MainColors.BROWN)
                        )
                    }, onClick = {
                        saveColor(context = context, color = MainColors.BROWN)
                        viewModel.setColor(MainColors.BROWN)
                        expandColorMenu = false
                    })
                    Divider()
                    DropdownMenuItem(text = {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_baseline_palette_24),
                            contentDescription = null,
                            tint = getMainColor(MainColors.BLUE)
                        )
                    }, onClick = {
                        saveColor(context = context, color = MainColors.BLUE)
                        viewModel.setColor(MainColors.BLUE)
                        expandColorMenu = false
                    })
                    Divider()
                    DropdownMenuItem(text = {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_baseline_palette_24),
                            contentDescription = null,
                            tint = getMainColor(MainColors.ORANGE)
                        )
                    }, onClick = {
                        saveColor(context = context, color = MainColors.ORANGE)
                        viewModel.setColor(MainColors.ORANGE)
                        expandColorMenu = false
                    })
                    Divider()
                    DropdownMenuItem(text = {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_baseline_palette_24),
                            contentDescription = null,
                            tint = getMainColor(MainColors.TEAL)
                        )
                    }, onClick = {
                        saveColor(context = context, color = MainColors.TEAL)
                        viewModel.setColor(MainColors.TEAL)
                        expandColorMenu = false
                    })
                }
            }
        }
    }
}

private fun getMainColor(color: String): Color {
    return when (color) {
        MainColors.TEAL -> Teal900
        MainColors.BLUE -> Blue800
        MainColors.BROWN -> Brown700
        MainColors.ORANGE -> Orange900
        else -> Teal900
    }
}

private fun saveColor(context: Context, color: String) {
    val preferences =
        context.getSharedPreferences("mainColor", Context.MODE_PRIVATE)
    preferences
        .edit()
        .putString("currentColor", color)
        .apply()
}