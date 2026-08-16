package com.avika.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.avika.app.data.repository.LocalClinicRepository
import com.avika.app.data.repository.LocalVenueRepository
import com.avika.app.navigation.AvikaNavHost
import com.avika.app.navigation.bottomNavItems
import com.avika.app.ui.theme.AvikaTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AvikaTheme {
                Surface {
                    AvikaApp()
                }
            }
        }
    }
}

@Composable
private fun AvikaApp() {
    val navController = rememberNavController()
    val clinicRepository = remember { LocalClinicRepository() }
    val venueRepository = remember { LocalVenueRepository() }

    Scaffold(
        bottomBar = {
            val backStackEntry by navController.currentBackStackEntryAsState()
            val currentDestination = backStackEntry?.destination
            NavigationBar {
                bottomNavItems.forEach { item ->
                    val selected = currentDestination?.hierarchy?.any { it.route == item.screen.route } == true
                    NavigationBarItem(
                        selected = selected,
                        onClick = {
                            navController.navigate(item.screen.route) {
                                popUpTo(navController.graph.startDestinationId) { saveState = true }
                                launchSingleTop = true
                                restoreState = true
                            }
                        },
                        icon = { Icon(item.icon, contentDescription = item.label) },
                        label = { Text(item.label) },
                    )
                }
            }
        },
    ) { padding ->
        AvikaNavHost(
            navController = navController,
            clinicRepository = clinicRepository,
            venueRepository = venueRepository,
            modifier = Modifier.padding(padding),
        )
    }
}
