package com.example.counterproject

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import kotlinx.coroutines.launch


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NavigationDrawerMenu() {
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
    val scope= rememberCoroutineScope()
    val drawerState = rememberDrawerState(DrawerValue.Closed)
    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            DrawerContent(
                items = items,
                onItemClick = { route ->
                    navController.navigate(route) {
                        popUpTo(navController.graph.startDestinationId) {
                            saveState = true
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                    scope.launch {
                        drawerState.close()
                    }

                },
                currentRoute = getCurrentRoute(navController)
            )
        }
    ){
        Scaffold(
            topBar = {
                TopAppBar(
                    title = { Text(text = "Test App") },
                    navigationIcon = {
                        IconButton(
                            onClick ={
                                scope.launch {
                                    drawerState.open()
                                }
                            }
                        ) {
                            Icon(
                              painter = painterResource( R.drawable.baseline_menu_24),
                                contentDescription = "Menu"
                            )
                        }
                    }
                )
            }
        ) { padding ->
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

}

@Composable
fun SearchScreen() {
    Column (
        modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ){
        Text(text = "Search Screen")
    }
}

@Composable
fun SettingsScreen() {
    Column (
        modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ){
        Text(text = "Settings Screen")
    }}

@Composable
fun ProfileScreen() {
    Column (
        modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ){
        Text(text = "Profile Screen")
    }
}

@Composable
fun HomeScreen() {
    Column (
        modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ){
        Text(text = "Home Screen")
    }
}

@Composable
fun getCurrentRoute(navController: NavHostController): String {
    return navController.currentBackStackEntry?.destination?.route ?: ""
}

@Composable
fun DrawerContent(
    items: List<NavigationItem>,
    onItemClick: (String) -> Unit,
    currentRoute: String
) {
    Column(
        modifier = Modifier
            .fillMaxWidth(.8f)
            .fillMaxHeight()
            .background(
                Color.White
            )
    ) {
        Text(
            "Test App",
            fontSize = 24.sp,
            color = Color.Cyan,
            modifier = Modifier.background(Color.Black)
        )

        HorizontalDivider()
        items.forEach { item ->
            NavigationDrawerItem(
                label = { Text(text = item.title) },
                icon = { Icon(painter = painterResource(id = item.icon), contentDescription = item.title) },
                selected = item.route == currentRoute,
                onClick = { onItemClick(item.route) }
            )

        }
    }


}