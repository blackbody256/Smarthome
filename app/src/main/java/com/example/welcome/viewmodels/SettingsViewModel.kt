package com.example.welcome

import android.content.Context
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class SettingsViewModel(private val context: Context) : ViewModel() {

    // Shared Preferences setup
    private val sharedPreferences = context.getSharedPreferences("app_settings", Context.MODE_PRIVATE)
    private val editor = sharedPreferences.edit()

    // State variables
    var name = mutableStateOf(sharedPreferences.getString("name", "") ?: "")
        private set

    var email = mutableStateOf(sharedPreferences.getString("email", "") ?: "")
        private set

    private val _selectedColor = MutableStateFlow(
        sharedPreferences.getString("color", "#FCFF00") ?: "#FCFF00"
    )
    val selectedColor = _selectedColor.asStateFlow()

    var autoArmSecurity = mutableStateOf(sharedPreferences.getBoolean("autoArmSecurity", false))
        private set

    var notifications = mutableStateOf(sharedPreferences.getBoolean("notifications", true))
        private set

    // Functions to update settings
    fun updateName(newName: String) {
        name.value = newName
        editor.putString("name", newName).apply()
    }

    fun updateEmail(newEmail: String) {
        email.value = newEmail
        editor.putString("email", newEmail).apply()
    }

    fun updateColor(newColor: String) {
        _selectedColor.value = newColor
        editor.putString("color", newColor).apply()
    }

    fun updateAutoArmSecurity(enabled: Boolean) {
        autoArmSecurity.value = enabled
        editor.putBoolean("autoArmSecurity", enabled).apply()
    }

    fun updateNotifications(enabled: Boolean) {
        notifications.value = enabled
        editor.putBoolean("notifications", enabled).apply()
    }
}

class SettingsViewModelFactory(private val context: Context) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(SettingsViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return SettingsViewModel(context) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}