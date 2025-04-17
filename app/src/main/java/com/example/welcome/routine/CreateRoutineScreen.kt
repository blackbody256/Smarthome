package com.example.welcome.routine

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.welcome.presentation.RoutineState
import com.example.welcome.presentation.RoutineEvents
import com.example.welcome.presentation.RoutineViewModel

@Composable
fun CreateRoutineScreen(
    state: RoutineState,
    onEvent: (RoutineEvents) -> Unit,
    onNavigateBack: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            text = "Create Routine",
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        OutlinedTextField(
            value = state.taskName.value,
            onValueChange = { onEvent(RoutineEvents.updateTaskName(it)) },
            label = { Text("Task Name") },
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = state.time.value,
            onValueChange = { onEvent(RoutineEvents.updateTime(it)) },
            label = { Text("Time") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp)
        )

        OutlinedTextField(
            value = state.recurrence.value,
            onValueChange = { onEvent(RoutineEvents.updateRecurrence(it)) },
            label = { Text("Recurrence") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp)
        )

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 24.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Button(onClick = onNavigateBack) {
                Text("Cancel")
            }
            Button(onClick = {
                onEvent(RoutineEvents.saveRoutine(state.taskName.value, state.time.value, state.recurrence.value))
                onNavigateBack()
            }) {
                Text("Save")
            }
        }
    }
}
