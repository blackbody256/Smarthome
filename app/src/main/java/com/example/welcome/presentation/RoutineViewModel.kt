package com.example.welcome.presentation

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

import androidx.lifecycle.viewModelScope
import com.example.welcome.data.RoutineDAO
import com.example.welcome.data.Routine
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update

import kotlinx.coroutines.launch

class RoutineViewModel(private val dao: RoutineDAO) : ViewModel() {
    val _state: MutableStateFlow<RoutineState> = MutableStateFlow(RoutineState())
    val state = combine(_state, dao.getAllRoutines()) { state, routines -> state.copy(routines = routines) }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), RoutineState())
    private val _createdRoutineId = MutableStateFlow(0)
    val createdRoutineId = _createdRoutineId.asStateFlow()

    fun setCreatedRoutineId(id: Int) {
        _createdRoutineId.value = id
    }


    fun onEvent(event: RoutineEvents) {
        when (event) {
            is RoutineEvents.deleteRoutine -> {
                viewModelScope.launch {
                    dao.deleteRoutine(event.routine)
                }
            }

            is RoutineEvents.saveRoutine -> {
                val routine = Routine(
                    taskName = state.value.taskName.value,
                    time = state.value.time.value,
                    recurrence = state.value.recurrence.value
                )
                viewModelScope.launch {
                    val id = dao.upsertRoutine(routine).toInt()
                    _createdRoutineId.value = id


                }
                _state.update {
                    it.copy(
                        taskName = mutableStateOf(""),
                        time = mutableStateOf(""),
                        recurrence = mutableStateOf("")) }
            }

            is RoutineEvents.startEditing -> {
                _state.update {
                    it.copy(
                        taskName = mutableStateOf(event.routine.taskName),
                        time = mutableStateOf(event.routine.time),
                        recurrence = mutableStateOf(event.routine.recurrence),
                        editingRoutineId = event.routine.id
                    )
                }
            }

            is RoutineEvents.updateRoutine -> {
                viewModelScope.launch {
                    dao.upsertRoutine(event.routine)
                }
                _state.update {
                    it.copy(
                        taskName = mutableStateOf(""),
                        time = mutableStateOf(""),
                        recurrence = mutableStateOf(""),
                        editingRoutineId = null
                    )
                }
            }



            is RoutineEvents.updateTaskName -> _state.update {
                it.copy(taskName = mutableStateOf(event.taskName))
            }
            is RoutineEvents.updateTime -> _state.update {
                it.copy(time = mutableStateOf(event.time))
            }
            is RoutineEvents.updateRecurrence -> _state.update {
                it.copy(recurrence = mutableStateOf(event.recurrence))
            }

            is RoutineEvents.getRoutineById -> {
                viewModelScope.launch {
                    val routine= dao.getRoutineById(event.id)
                    // Update the state with the fetched routine details
                    _state.value = _state.value.copy(
                        editingRoutineId = routine.id,
                        taskName = mutableStateOf(routine.taskName),
                        time = mutableStateOf(routine.time),
                        recurrence = mutableStateOf(routine.recurrence)

                    )
                }
            }




        }
    }
}
