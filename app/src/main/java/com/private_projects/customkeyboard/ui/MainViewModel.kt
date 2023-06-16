package com.private_projects.customkeyboard.ui

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.text.intl.Locale
import androidx.compose.ui.text.toLowerCase
import androidx.compose.ui.text.toUpperCase
import androidx.lifecycle.ViewModel
import com.private_projects.customkeyboard.data.MainColors

class MainViewModel : ViewModel() {
    private var _currentLocale = mutableStateOf(BACKSPACE_CAPS_ENG_TEXT)
    val currentLocale: State<String> = _currentLocale

    private val _text = mutableStateOf("")
    val text: State<String> = _text

    private val _isCaps = mutableStateOf(true)
    val isCaps: State<Boolean> = _isCaps

    private val _mainColor = mutableStateOf(MainColors.TEAL)
    val mainColor: State<String> = _mainColor

    fun changeLocale() {
        if (_currentLocale.value == BACKSPACE_ENG_TEXT ||
            _currentLocale.value == BACKSPACE_CAPS_ENG_TEXT
        ) {
            if (isCaps.value) {
                _currentLocale.value = BACKSPACE_CAPS_RUS_TEXT
            } else {
                _currentLocale.value = BACKSPACE_RUS_TEXT
            }
        } else {
            if (isCaps.value) {
                _currentLocale.value = BACKSPACE_CAPS_ENG_TEXT
            } else {
                _currentLocale.value = BACKSPACE_ENG_TEXT
            }
        }
    }

    fun setColor(color: String) {
        _mainColor.value = color
    }

    fun onDigitKeyPressed(key: Int) {
        _text.value = key.toString()
    }

    fun onTextKeyPressed(key: String) {
        if (_isCaps.value) {
            _text.value = key.toUpperCase(Locale.current)
            onCapsKeyPressed()
        } else {
            _text.value = key.toLowerCase(Locale.current)
        }
    }

    fun onCapsKeyPressed() {
        _isCaps.value = !_isCaps.value
        if (_isCaps.value) {
            if (_currentLocale.value == BACKSPACE_ENG_TEXT) {
                _currentLocale.value = BACKSPACE_CAPS_ENG_TEXT
            } else if (_currentLocale.value == BACKSPACE_RUS_TEXT) {
                _currentLocale.value = BACKSPACE_CAPS_RUS_TEXT
            }
        } else {
            if (_currentLocale.value == BACKSPACE_CAPS_ENG_TEXT) {
                _currentLocale.value = BACKSPACE_ENG_TEXT
            } else if (_currentLocale.value == BACKSPACE_CAPS_RUS_TEXT) {
                _currentLocale.value = BACKSPACE_RUS_TEXT
            }
        }
    }
}