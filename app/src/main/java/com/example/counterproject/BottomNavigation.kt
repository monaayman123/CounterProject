package com.example.counterproject

import android.net.http.SslCertificate.saveState
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavController

@Composable
fun BottomNavigation(
    items: List<NavigationItem>,
    navController: NavController,
) {
    val navBackStackEntry = navController.currentBackStackEntry
    val currentRoute = navBackStackEntry?.destination?.route
    NavigationBar {
        items.forEach { item ->
            val selected = currentRoute == item.route
            NavigationBarItem(
                selected = selected,
                onClick = {
                    navController.navigate(item.route) {
                        navController.graph.startDestinationId?.let{
                            popUpTo(it){
                                saveState = true
                            }
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                },
                icon = {
                    Icon(
                        painter = painterResource(id = item.icon),
                        contentDescription = ""
                    )
                },
                label = {
                    Text("${item.title}")
                }


            )
        }


    }
}