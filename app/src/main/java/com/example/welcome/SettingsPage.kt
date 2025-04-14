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




@Composable
fun SettingsPage(modifier: Modifier = Modifier) {
    val viewModel: SettingsViewModel = viewModel(
        factory = SettingsViewModelFactory(LocalContext.current)
    )

    val name by remember { viewModel.name }
    val email by remember { viewModel.email }
    val autoArmSecurity by remember { viewModel.autoArmSecurity }
    val notifications by remember { viewModel.notifications }
    val selectedColor by viewModel.selectedColor.collectAsState()

    var showColorPicker by remember { mutableStateOf(false) }

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text(
            text = "Profile Information",
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold
        )

        OutlinedTextField(
            value = name,
            onValueChange = { viewModel.updateName(it) },
            label = { Text("Name") },
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = email,
            onValueChange = { viewModel.updateEmail(it) },
            label = { Text("Email") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "Appearance",
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold
        )

        // Color selector
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clickable { showColorPicker = true }
                .padding(vertical = 8.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text("TopBar Color")
            Box(
                modifier = Modifier
                    .size(24.dp)
                    .background(
                        Color(android.graphics.Color.parseColor(selectedColor)),
                        shape = MaterialTheme.shapes.small
                    )
            )
        }

        if (showColorPicker) {
            Card(
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text("Select Color", fontWeight = FontWeight.Bold)
                    Spacer(modifier = Modifier.height(8.dp))

                    val colors = listOf("#6200EE", "#03DAC5", "#FF6200", "#FF0266", "#3700B3", "#FCFF00")

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceEvenly
                    ) {
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
                                        viewModel.updateColor(color)
                                        showColorPicker = false
                                    }
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(8.dp))
                    Button(
                        onClick = { showColorPicker = false },
                        modifier = Modifier.align(Alignment.End)
                    ) {
                        Text("Close")
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "Security & Notifications",
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold
        )

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text("Auto Arm Security")
            Switch(
                checked = autoArmSecurity,
                onCheckedChange = { viewModel.updateAutoArmSecurity(it) }
            )
        }

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text("Enable Notifications")
            Switch(
                checked = notifications,
                onCheckedChange = { viewModel.updateNotifications(it) }
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun SettingsPagePreview() {
    SettingsPage()
}