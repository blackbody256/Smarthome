package com.example.welcome

import android.content.Context
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

/**
 * This is ViewModel for the Settings screen that manages user preferences
 * and persists them using SharedPreferences.
 *
 *
 */
class SettingsViewModel(private val context: Context) : ViewModel() {

    // Set up SharedPreferences to persistently store settings
    // "app_settings" is the name of the preferences file
    private val sharedPreferences = context.getSharedPreferences("app_settings", Context.MODE_PRIVATE)

    // this is the editor instance used to modify SharedPreferences
    private val editor = sharedPreferences.edit()

    // User's name - mutableStateOf is a Compose state holder that triggers recomposition when changed
    // We initialize it from SharedPreferences with a default empty string if no value exists
    // private set restricts modification from outside the class
    var name = mutableStateOf(sharedPreferences.getString("name", "") ?: "")
        private set

    // User's email with same pattern as name
    var email = mutableStateOf(sharedPreferences.getString("email", "") ?: "")
        private set

    // Color selection - using StateFlow instead of mutableStateOf
    // StateFlow is part of Kotlin coroutines and offers more features for state management
    private val _selectedColor = MutableStateFlow(
        sharedPreferences.getString("color", "#FCFF00") ?: "#FCFF00" // Default to yellow color
    )
    // this is public accessor which is read-only StateFlow
    val selectedColor = _selectedColor.asStateFlow()

    // Boolean preference for auto arming security feature
    var autoArmSecurity = mutableStateOf(sharedPreferences.getBoolean("autoArmSecurity", false))
        private set

    // Boolean preference for notifications, defaults to true
    var notifications = mutableStateOf(sharedPreferences.getBoolean("notifications", true))
        private set

    /**
     * Updates the user's name in both the UI state and SharedPreferences
     *
     */
    fun updateName(newName: String) {
        name.value = newName             // Update the state for the UI
        editor.putString("name", newName).apply()  // Persist to SharedPreferences
    }

    /**
     * Updates the user's email in both the UI state and SharedPreferences
     *
     */
    fun updateEmail(newEmail: String) {
        email.value = newEmail
        editor.putString("email", newEmail).apply()
    }

    /**
     * Updates the selected color theme in both the UI state and SharedPreferences
     *
     */
    fun updateColor(newColor: String) {
        _selectedColor.value = newColor  // Update StateFlow value
        editor.putString("color", newColor).apply()
    }

    /**
     * Updates the auto-arm security setting
     *
     */
    fun updateAutoArmSecurity(enabled: Boolean) {
        autoArmSecurity.value = enabled
        editor.putBoolean("autoArmSecurity", enabled).apply()
    }

    /**
     * Updates the notifications setting
     *
     */
    fun updateNotifications(enabled: Boolean) {
        notifications.value = enabled
        editor.putBoolean("notifications", enabled).apply()
    }
}

/**
 * Factory class for creating SettingsViewModel instances.
 * This is needed because SettingsViewModel requires a Context parameter.
 *
 * ViewModelProvider cannot create ViewModels with non-empty constructors by default,
 * so we need this factory to tell it how to create our ViewModel.
 *
 */
class SettingsViewModelFactory(private val context: Context) : ViewModelProvider.Factory {

    /**
     * Creates a new instance of the ViewModel
     *
     */
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        // Check if the requested class is SettingsViewModel or a subclass
        if (modelClass.isAssignableFrom(SettingsViewModel::class.java)) {
            // Cast is needed because the create method returns a generic type T
            @Suppress("UNCHECKED_CAST")
            return SettingsViewModel(context) as T
        }
        // If we get here, the requested class is not supported
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}