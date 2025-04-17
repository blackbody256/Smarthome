package com.example.welcome
import com.example.welcome.ui.theme.WelcomeTheme
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.Create
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings
import androidx.compose.ui.platform.LocalContext
import android.content.SharedPreferences
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

import androidx.compose.ui.graphics.vector.ImageVector
import android.content.Context
import androidx.activity.viewModels
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.rememberNavController
import com.example.welcome.data.RoutineDatabase
import com.example.welcome.navroutes.Screen
import com.example.welcome.presentation.RoutineViewModel


class MainActivity : ComponentActivity() {

    private val routineViewModel: RoutineViewModel by viewModels {
        object : ViewModelProvider.Factory {
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                return RoutineViewModel(RoutineDatabase.getDatabase(applicationContext).dao) as T
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            WelcomeTheme {
                ScaffoldDemo(routineViewModel)
            }
        }
    }
}
// Define the Navitem class
data class Navitem(
    val label: String,
    val icon: ImageVector
)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ScaffoldDemo(routineViewModel: RoutineViewModel) {
    val navController = rememberNavController()
    val context = LocalContext.current
    val sharedPreferences = context.getSharedPreferences("app_settings", Context.MODE_PRIVATE)

    val topBarColor = remember {
        mutableStateOf(sharedPreferences.getString("color", "#FCFF00") ?: "#FCFF00")
    }

    val navitemList = listOf(
        Navitem("Favorites", Icons.Default.Star),
        Navitem("Things", Icons.Default.ShoppingCart),
        Navitem("Routines", Icons.Default.AccountBox),
        Navitem("Ideas", Icons.Default.Notifications),
        Navitem("Settings", Icons.Default.Settings),
    )

    var selectedIndex by remember { mutableIntStateOf(0) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "My Smart Home",
                        textAlign = TextAlign.Center,
                        modifier = Modifier.padding(horizontal = 60.dp),
                        fontWeight = FontWeight.SemiBold
                    )
                },
                colors = TopAppBarColors(
                    containerColor = Color(android.graphics.Color.parseColor(topBarColor.value)),
                    scrolledContainerColor = MaterialTheme.colorScheme.onPrimary,
                    navigationIconContentColor = MaterialTheme.colorScheme.onPrimary,
                    titleContentColor = Color.White,
                    actionIconContentColor = MaterialTheme.colorScheme.onPrimary
                ),
                actions = {
                    if (selectedIndex == 1) {
                        IconButton(onClick = {}) {
                            Icon(imageVector = Icons.Default.Search, contentDescription = null)
                        }
                        IconButton(onClick = {}) {
                            Icon(imageVector = Icons.Default.Menu, contentDescription = null)
                        }
                    } else if (selectedIndex == 2) {
                        IconButton(onClick = {
                            navController.navigate(Screen.CreateRoutine.route)
                        }) {
                            Icon(imageVector = Icons.Default.Create, contentDescription = null)
                        }
                    }
                }
            )
        },
        content = { innerPadding ->
            Box(modifier = Modifier.padding(innerPadding)) {
                NavigationGraph(navController = navController, routineViewModel = routineViewModel)
            }
        },
        bottomBar = {
            NavigationBar {
                navitemList.forEachIndexed { index, navitem ->
                    NavigationBarItem(
                        selected = selectedIndex == index,
                        onClick = {
                            selectedIndex = index
                            when (index) {
                                0 -> navController.navigate(Screen.Favorites.route)
                                1 -> navController.navigate(Screen.Things.route)
                                2 -> navController.navigate(Screen.RoutinesList.route)
                                3 -> navController.navigate(Screen.Ideas.route)
                                4 -> navController.navigate(Screen.Settings.route)
                            }
                        },
                        icon = {
                            Icon(
                                imageVector = navitem.icon,
                                contentDescription = "Icon",
                                tint = if (selectedIndex == index)
                                    Color(android.graphics.Color.parseColor(topBarColor.value))
                                else Color.Gray
                            )
                        },
                        label = { Text(text = navitem.label) }
                    )
                }
            }
        }
    )

    DisposableEffect(true) {
        val listener = SharedPreferences.OnSharedPreferenceChangeListener { prefs, key ->
            if (key == "color") {
                topBarColor.value = prefs.getString(key, "#FCFF00") ?: "#FCFF00"
            }
        }
        sharedPreferences.registerOnSharedPreferenceChangeListener(listener)

        onDispose {
            sharedPreferences.unregisterOnSharedPreferenceChangeListener(listener)
        }
    }
}




