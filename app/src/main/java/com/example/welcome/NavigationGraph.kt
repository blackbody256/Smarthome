package com.example.welcome

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.welcome.navroutes.Screen
import com.example.welcome.presentation.RoutineEvents
import com.example.welcome.presentation.RoutineViewModel
import com.example.welcome.routine.CreateRoutineScreen
import com.example.welcome.routine.EditRoutineScreen
import com.example.welcome.routine.RoutinesScreen

//@Composable
//fun NavigationGraph(navController: NavHostController, routineViewModel: RoutineViewModel) {
//    val state by routineViewModel.state.collectAsState()
//    NavHost(navController = navController, startDestination = "favorites") {
//        composable("favorites") { favPage() }
//        composable("things") { HomePage() }
//        composable("routines") {
//            RoutinesScreen(
//                state = state,
//                navController = navController,
//                onEvent = routineViewModel::onEvent
//            )
//        }
//        composable("createRoutine") {
//            CreateRoutineScreen(
//                state = state,
//                onEvent = routineViewModel::onEvent,
//                onNavigateBack = { navController.popBackStack() }
//            )
//        }
//
//        composable("editRoutine") {
//            EditRoutineScreen(
//                state = state,
//                onEvent = routineViewModel::onEvent,
//                onNavigateBack = { navController.popBackStack() }
//            )
//        }
//
//        composable("ideas") { Text("Ideas coming soon!") }
//        composable("settings") { SettingsPage() }
//    }
//}
@Composable
fun NavigationGraph(navController: NavHostController, routineViewModel: RoutineViewModel) {
    val state by routineViewModel.state.collectAsState()

    NavHost(navController = navController, startDestination = Screen.Favorites.route) {
        composable(Screen.Favorites.route) { favPage() }
        composable(Screen.Things.route) { HomePage() }
        composable(Screen.RoutinesList.route) {
            RoutinesScreen(
                state = state,
                navController = navController,
                onEvent = routineViewModel::onEvent
            )
        }
        composable(Screen.CreateRoutine.route) {
            CreateRoutineScreen(
                state = state,
                onEvent = routineViewModel::onEvent,
                onNavigateBack = { navController.popBackStack() }
            )
        }

        composable(route = Screen.EditRoutine.route) {
            val createdRoutineId by routineViewModel.createdRoutineId.collectAsState()
            LaunchedEffect(createdRoutineId) {
                if (createdRoutineId != 0) {
                    routineViewModel.onEvent(RoutineEvents.getRoutineById(createdRoutineId))
                }
            }
            EditRoutineScreen(
                state = state,
                onEvent = routineViewModel::onEvent,
                onNavigateBack = { navController.popBackStack() }
            )
        }


        composable(Screen.Ideas.route) { Text("Ideas coming soon!") }
        composable(Screen.Settings.route) { SettingsPage() }
    }
}

