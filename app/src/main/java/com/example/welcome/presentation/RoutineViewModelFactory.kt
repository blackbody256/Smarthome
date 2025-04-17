package com.example.welcome.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.welcome.data.RoutineDAO


class RoutineViewModelFactory(private val dao: RoutineDAO) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(RoutineViewModel::class.java)) {
            return RoutineViewModel(dao) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
