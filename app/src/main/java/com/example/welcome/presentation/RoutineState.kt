package com.example.welcome.presentation

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import com.example.welcome.data.Routine

data class RoutineState (
    val routines: List<Routine> = emptyList(),
    val taskName: MutableState<String> = mutableStateOf(""),
    val time: MutableState<String> = mutableStateOf(""),
    val recurrence: MutableState<String> = mutableStateOf(""),
    val editingRoutineId: Int? = null
)