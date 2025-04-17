package com.example.welcome.navroutes

sealed class Screen(val route: String) {
    object Favorites : Screen("favorites")
    object Things : Screen("things")
    object RoutinesList : Screen("routines")
    object CreateRoutine : Screen("createRoutine")
    object EditRoutine : Screen("editRoutine/{id}")
    object Ideas : Screen("ideas")
    object Settings : Screen("settings")
}
