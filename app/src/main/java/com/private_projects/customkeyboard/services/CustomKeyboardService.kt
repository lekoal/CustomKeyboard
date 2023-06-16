package com.private_projects.customkeyboard.services

import android.view.View
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelStore
import androidx.lifecycle.ViewModelStoreOwner
import androidx.lifecycle.setViewTreeLifecycleOwner
import androidx.lifecycle.setViewTreeViewModelStoreOwner
import androidx.savedstate.SavedStateRegistry
import androidx.savedstate.SavedStateRegistryController
import androidx.savedstate.SavedStateRegistryOwner
import androidx.savedstate.setViewTreeSavedStateRegistryOwner
import com.private_projects.customkeyboard.ui.CustomKeyboardView

class CustomKeyboardService : LifecycleInputMethodService(), ViewModelStoreOwner,
    SavedStateRegistryOwner { //Сервис для работы клавиатуры в качестве системной
    private val savedStateRegistryController = SavedStateRegistryController.create(this)
    override fun onCreateInputView(): View {
        val view = CustomKeyboardView(this)
        window?.window?.decorView?.let {  decorView ->
            decorView.setViewTreeLifecycleOwner(this)
            decorView.setViewTreeViewModelStoreOwner(this)
            decorView.setViewTreeSavedStateRegistryOwner(this)
        }
        return view
    }

    override fun onCreate() {
        super.onCreate()
        savedStateRegistryController.performRestore(null)
    }

    override val lifecycle: Lifecycle
        get() = dispatcher.lifecycle
    override val savedStateRegistry: SavedStateRegistry
        get() = savedStateRegistryController.savedStateRegistry
    override val viewModelStore: ViewModelStore
        get() = ViewModelStore()
}