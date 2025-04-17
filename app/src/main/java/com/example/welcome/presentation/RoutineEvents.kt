package com.example.welcome.presentation

import com.example.welcome.data.Routine

sealed class RoutineEvents {
    data class deleteRoutine(val routine: Routine) : RoutineEvents()

    data class saveRoutine(
        val taskName: String,
        val time: String,
        val recurrence: String

    ) : RoutineEvents()



    data class updateTaskName(val taskName: String) : RoutineEvents()
    data class updateTime(val time: String) : RoutineEvents()
    data class updateRecurrence(val recurrence: String) : RoutineEvents()

    data class startEditing(val routine: Routine) : RoutineEvents()
    data class updateRoutine(val routine: Routine) : RoutineEvents()

    data class getRoutineById(val id: Int) : RoutineEvents()
    }

