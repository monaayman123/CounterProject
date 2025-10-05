package com.example.counterproject

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController


@Composable
fun BottomScreen() {
    val navController = rememberNavController()

    val items = listOf(
        NavigationItem(
            title = "Home",
            icon = R.drawable.baseline_home_24,
            route = "home"
        ),
        NavigationItem(
            title = "Profile",
            icon = R.drawable.outline_person_24,
            route = "profile"
        ),
        NavigationItem(
            title = "Settings",
            icon = R.drawable.baseline_settings_suggest_24,
            route = "settings"
        ),
        NavigationItem(
            title = "Search",
            icon = R.drawable.outline_search_24,
            route = "search"
        )

    )
    Scaffold(
        bottomBar = {
            BottomNavigation(
                items = items,
                navController = navController
            )
        }

    ){padding ->
        NavHost(
            navController=navController,
            startDestination = "home",
            modifier = Modifier.padding(padding)
        ) {
            composable("home") {
                HomeScreen()
            }
            composable("profile") {
                ProfileScreen()
            }
            composable("settings") {
                SettingsScreen()
            }
            composable("search") {
                SearchScreen()
            }
        }

    }
}