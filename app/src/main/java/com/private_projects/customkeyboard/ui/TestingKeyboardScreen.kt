package com.private_projects.customkeyboard.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.private_projects.customkeyboard.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TestingKeyboardScreen(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        var text by rememberSaveable {
            mutableStateOf("")
        }
        TextField(value = text,
            onValueChange = {
                text = it
            }, label = {
                Text(text = stringResource(id = R.string.testing_text_field))
            }, placeholder = {
                Text(text = stringResource(R.string.testing_text_field_placeholder_text),
                modifier = modifier.padding(5.dp))
            })
    }
}