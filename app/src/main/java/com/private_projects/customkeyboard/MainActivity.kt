package com.private_projects.customkeyboard

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.provider.Settings
import android.view.inputmethod.InputMethodManager
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.ui.Modifier
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.private_projects.customkeyboard.ui.MainScreen
import com.private_projects.customkeyboard.ui.MainViewModel
import com.private_projects.customkeyboard.ui.TestingKeyboardScreen
import com.private_projects.customkeyboard.ui.theme.CustomKeyboardTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val viewModel = ViewModelProvider(this)[MainViewModel::class.java]
        setContent {
            val navController = rememberNavController()
            CustomKeyboardTheme {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(color = MaterialTheme.colorScheme.background)
                ) {
                    NavHost(
                        navController = navController,
                        startDestination = "main_screen"
                    ) {
                        composable("main_screen") {
                            MainScreen(viewModel = viewModel, navController = navController) {
                                val imm = getSystemService(Context.INPUT_METHOD_SERVICE)
                                        as InputMethodManager
                                if (isCustomKeyboardEnabled(imm, this@MainActivity.packageName)) {
                                    imm.showInputMethodPicker() //Позволяет выбрать клавиатуру по умолчанию
                                    imm.showSoftInput(currentFocus, 0)
                                } else {
                                    val intent = Intent(Settings.ACTION_INPUT_METHOD_SETTINGS)
                                    startActivity(intent) //Позволяет включить клавиатуру в системе
                                }
                            }
                        }
                        composable("testing_keyboard_screen") {
                            TestingKeyboardScreen()
                        }
                    }
                }
            }
        }
    }

    private fun isCustomKeyboardEnabled(
        inputMethodManager: InputMethodManager,
        packageName: String
    ): Boolean {
        val enabledInputMethods = inputMethodManager.enabledInputMethodList
        for (enabledInputMethod in enabledInputMethods) {
            if (enabledInputMethod.packageName == packageName) {
                return true
            }
        }
        return false
    }
}