package com.example.welcome.routine

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.welcome.data.Routine

@Composable

fun RoutineItem(
    routine: Routine,
    onClick: (Routine) -> Unit = {}
) {
    Column(
        modifier = Modifier
            .clickable { onClick(routine) }
            .fillMaxWidth()
            .padding(vertical = 8.dp)
    ) {
        Text("Task Name: ${routine.taskName}", fontWeight = FontWeight.SemiBold)
        Text("Timing: ${routine.time}")
        Text("Recurrence: ${routine.recurrence}")
        Divider(modifier = Modifier.padding(top = 8.dp))
    }
}

