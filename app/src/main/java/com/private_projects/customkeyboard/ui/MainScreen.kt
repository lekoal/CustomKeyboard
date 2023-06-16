package com.private_projects.customkeyboard.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.private_projects.customkeyboard.R

@Composable
fun MainScreen(
    modifier: Modifier = Modifier,
    viewModel: MainViewModel,
    navController: NavController,
    startIntent: () -> Unit) { //Главный экран для отображения кнопок и клавиатуры
    Column(
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            FilledTonalButton(
                onClick = startIntent,
                shape = RoundedCornerShape(10.dp)
            ) {
                Text(text = stringResource(R.string.set_keyboard_button_text))
            }
            FilledTonalButton(
                onClick = { navController.navigate("testing_keyboard_screen") },
                shape = RoundedCornerShape(10.dp)
            ) {
                Text(text = stringResource(R.string.test_keyboard_button_text))
            }
        }
        CustomKeyboard(viewModel = viewModel, isBlocked = true)
    }
}