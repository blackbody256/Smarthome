package com.example.welcome.routine

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.History

import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.welcome.data.Routine
import com.example.welcome.data.RoutineDAO
import com.example.welcome.data.RoutineDatabase
import com.example.welcome.navroutes.Screen
import com.example.welcome.presentation.RoutineEvents
import com.example.welcome.presentation.RoutineState
import com.example.welcome.presentation.RoutineViewModel
import com.example.welcome.presentation.RoutineViewModelFactory


@Composable
fun RoutinesScreen(state:RoutineState ,navController: NavHostController, onEvent: (RoutineEvents) -> Unit) {

    var showDialog by remember { mutableStateOf(false) }
    var selectedRoutine by remember { mutableStateOf<Routine?>(null) }
    var showOptionsDialog by remember { mutableStateOf(false) }
    var showDeleteConfirmation by remember { mutableStateOf(false) }
    val dao = RoutineDatabase.getDatabase(LocalContext.current).dao
    val routineViewModel: RoutineViewModel = viewModel(
        factory = RoutineViewModelFactory(dao)
    )



    Box(modifier = Modifier.fillMaxSize()) {
        if (state.routines.isEmpty()) {
            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Icon(
                    imageVector = Icons.Default.History,
                    contentDescription = "No Routines",
                    modifier = Modifier.size(80.dp),
                    tint = Color.Gray
                )
                Text("No Routines!", style = MaterialTheme.typography.headlineSmall)
                Text("Click the '+' button below to get started")
            }
        } else {
            LazyColumn(modifier = Modifier.padding(16.dp)) {
                items(state.routines) { routine ->
                    RoutineItem(
                        routine = routine,
                        onClick = {
                            selectedRoutine = it
                            showOptionsDialog = true
                        }
                        )

                }
            }
        }

        FloatingActionButton(
            onClick = {
                state.taskName.value=""
                state.time.value=""
                state.recurrence.value=""
                navController.navigate("createRoutine")
            },
            containerColor = Color(0xFF00aae4),
            contentColor = Color.White,
            shape = CircleShape,
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(16.dp)
        ) {
            Icon(imageVector = Icons.Default.Add, contentDescription = "Add Routine")
        }

    }

    if (showOptionsDialog && selectedRoutine != null) {
        AlertDialog(
            onDismissRequest = { showOptionsDialog = false },
            title = { Text("Routine Options") },
            text = { Text("What would you like to do?") },
            confirmButton = {
                TextButton(onClick = {
                    routineViewModel.setCreatedRoutineId(selectedRoutine!!.id)

                    navController.navigate(Screen.EditRoutine.route)
                    showOptionsDialog = false
                }) {
                    Text("Edit")
                }
            },
            dismissButton = {
                TextButton(onClick = {
                    showOptionsDialog = false
                    showDeleteConfirmation = true
                }) {
                    Text("Delete")
                }
            }
        )
    }

    if (showDeleteConfirmation && selectedRoutine != null) {
        AlertDialog(
            onDismissRequest = { showDeleteConfirmation = false },
            title = { Text("Confirm Delete") },
            text = { Text("Are you sure you want to delete this Routine?") },
            confirmButton = {
                TextButton(onClick = {
                    onEvent(RoutineEvents.deleteRoutine(selectedRoutine!!))
                    showDeleteConfirmation = false
                    selectedRoutine = null
                }) {
                    Text("Delete", color = Color.Red)
                }
            },
            dismissButton = {
                TextButton(onClick = {
                    showDeleteConfirmation = false
                }) {
                    Text("Cancel")
                }
            }
        )
    }


}




