package com.example.welcome

import android.content.Context
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.foundation.background
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

/**
 * this is a composable function that displays the settings page with user preferences
 * including profile information, appearance settings, and security options.
 */
@Composable
fun SettingsPage(modifier: Modifier = Modifier) {
    // Get ViewModel instance using the factory that requires Context
    val viewModel: SettingsViewModel = viewModel(
        factory = SettingsViewModelFactory(LocalContext.current)
    )

    // Observe state values from the ViewModel
    // 'remember' helps preserve these observed values across recompositions
    val name by remember { viewModel.name }
    val email by remember { viewModel.email }
    val autoArmSecurity by remember { viewModel.autoArmSecurity }
    val notifications by remember { viewModel.notifications }

    // Using collectAsState() to convert StateFlow to Compose State
    val selectedColor by viewModel.selectedColor.collectAsState()

    // Local UI state to control color picker visibility
    var showColorPicker by remember { mutableStateOf(false) }

    // Main container column with scrolling enabled
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(rememberScrollState()), // Make the column scrollable
        verticalArrangement = Arrangement.spacedBy(16.dp) // Space between child elements
    ) {
        // Profile Information Section
        Text(
            text = "Profile Information",
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold
        )

        // Name input field
        OutlinedTextField(
            value = name,
            onValueChange = { viewModel.updateName(it) }, // Update name in ViewModel
            label = { Text("Name") },
            modifier = Modifier.fillMaxWidth()
        )

        // Email input field
        OutlinedTextField(
            value = email,
            onValueChange = { viewModel.updateEmail(it) }, // Update email in ViewModel
            label = { Text("Email") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp)) // Add vertical space

        // Appearance Section
        Text(
            text = "Appearance",
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold
        )

        // Color selector - clicking this row opens the color picker
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clickable { showColorPicker = true } // Toggle color picker visibility
                .padding(vertical = 8.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text("Theme Color")
            // Color preview box
            Box(
                modifier = Modifier
                    .size(24.dp)
                    .background(
                        // Parse the hex color string to a Color object
                        Color(android.graphics.Color.parseColor(selectedColor)),
                        shape = MaterialTheme.shapes.small
                    )
            )
        }

        // Conditional display of the color picker dialog
        if (showColorPicker) {
            Card(
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text("Select Color", fontWeight = FontWeight.Bold)
                    Spacer(modifier = Modifier.height(8.dp))

                    // Predefined color options in my list
                    val colors = listOf("#6200EE", "#03DAC5", "#FF6200", "#FF0266", "#3700B3", "#FCFF00")

                    // Color selection row with all available colors
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceEvenly
                    ) {
                        // Create a clickable box for each color option
                        colors.forEach { color ->
                            Box(
                                modifier = Modifier
                                    .size(40.dp)
                                    .padding(4.dp)
                                    .background(
                                        Color(android.graphics.Color.parseColor(color)),
                                        shape = MaterialTheme.shapes.small
                                    )
                                    .clickable {
                                        viewModel.updateColor(color) // Update selected color in ViewModel
                                        showColorPicker = false      // Close the picker
                                    }
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(8.dp))
                    // Close button for the color picker
                    Button(
                        onClick = { showColorPicker = false },
                        modifier = Modifier.align(Alignment.End)
                    ) {
                        Text("Close")
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp)) // Add vertical space

        // Security & Notifications Section
        Text(
            text = "Security & Notifications",
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold
        )

        // Auto Arm Security toggle
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text("Auto Arm Security")
            Switch(
                checked = autoArmSecurity,
                onCheckedChange = { viewModel.updateAutoArmSecurity(it) } // Update in ViewModel
            )
        }

        // Notifications toggle
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text("Enable Notifications")
            Switch(
                checked = notifications,
                onCheckedChange = { viewModel.updateNotifications(it) } // Update in ViewModel
            )
        }
    }
}

/**
 * Preview function for the SettingsPage composable.
 * This allows developers to see the UI in Android Studio without running the app.
 */
@Preview(showBackground = true)
@Composable
fun SettingsPagePreview() {
    SettingsPage()
}