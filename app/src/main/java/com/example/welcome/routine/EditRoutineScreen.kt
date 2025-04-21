package com.example.welcome.routine

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.welcome.data.Routine
import com.example.welcome.presentation.RoutineState
import com.example.welcome.presentation.RoutineEvents
import com.example.welcome.presentation.RoutineViewModel

import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.runtime.*

//imports for the time picker
import android.app.TimePickerDialog
import android.widget.TimePicker
import androidx.compose.ui.platform.LocalContext
import java.util.*
import java.util.Locale

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditRoutineScreen(
    state: RoutineState,
    onEvent: (RoutineEvents) -> Unit,
    onNavigateBack: () -> Unit
) {

    //2 variables for the time picker
    val context = LocalContext.current //enables Android to know exactly where to open up the TimePickerDialog.
    val calendar = Calendar.getInstance() //creating a calender object. These objects can have the hour and minute which we need for the timepicker.

    val recurrenceOptions = listOf("Day", "Week", "Month", "Year", "Week days", "Weekend")
    var expanded by remember { mutableStateOf(false) }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            text = "Edit Routine",
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        OutlinedTextField(
            value = state.taskName.value,
            onValueChange = { onEvent(RoutineEvents.updateTaskName(it)) },
            label = { Text("Task Name") },
            modifier = Modifier.fillMaxWidth()
        )

//        OutlinedTextField(
//            value = state.time.value,
//            onValueChange = { onEvent(RoutineEvents.updateTime(it)) },
//            label = { Text("Time") },
//            modifier = Modifier
//                .fillMaxWidth()
//                .padding(top = 16.dp)
//        )

        Button(
            onClick = {
                val hour = calendar.get(Calendar.HOUR_OF_DAY)  //we can get the our with our calender object.
                val minute = calendar.get(Calendar.MINUTE)  //we can get the minute too

                //TimePickerDialog is basically a pop up clock that lets you choose time.
                //recall context is where or how we want this clock to pop-up.
                //line 59->is a callback(lambda function with 3 parameters) function that stores the user's time selections.
                //locale allows for a more stable english version of time.
                TimePickerDialog(
                    context,
                    { _: TimePicker, selectedHour: Int, selectedMinute: Int ->
                        val amPm = if (selectedHour >= 12) "PM" else "AM"
                        val hour12 = if (selectedHour % 12 == 0) 12 else selectedHour % 12
                        val formattedTime = String.format(Locale.US,"%02d:%02d %s", hour12, selectedMinute, amPm)
                        onEvent(RoutineEvents.updateTime(formattedTime))
                    },
                    hour,
                    minute,
                    false //therefore you expect 12 hour am/pm
                ).show()
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp)
        ) {

            //text that appears before you pick time and after it is set.
            Text(
                if (state.time.value.isEmpty()) "Pick Time" else "Time: ${state.time.value}"
            )
        }

//        OutlinedTextField(
//            value = state.recurrence.value,
//            onValueChange = { onEvent(RoutineEvents.updateRecurrence(it)) },
//            label = { Text("Recurrence") },
//            modifier = Modifier
//                .fillMaxWidth()
//                .padding(top = 16.dp)
//        )
        ExposedDropdownMenuBox(
            expanded = expanded,
            onExpandedChange = { expanded = !expanded },
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp)
        ) {
            OutlinedTextField(
                value = state.recurrence.value,
                onValueChange = {},
                readOnly = true,
                label = { Text("Recurrence") },
                trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded) },
                modifier = Modifier
                    .menuAnchor()
                    .fillMaxWidth()
            )
            DropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false }
            ) {
                recurrenceOptions.forEach { option ->
                    DropdownMenuItem(
                        text = { Text(option) },
                        onClick = {
                            onEvent(RoutineEvents.updateRecurrence(option))
                            expanded = false
                        }
                    )
                }
            }
        }

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
                val updated = Routine(
                    id = state.editingRoutineId ?: return@Button, // no id? no update.
                    taskName = state.taskName.value,
                    time = state.time.value,
                    recurrence = state.recurrence.value
                )
                onEvent(RoutineEvents.updateRoutine(updated))
                onNavigateBack()
            }) {
                Text("Update")
            }
        }
    }
}
