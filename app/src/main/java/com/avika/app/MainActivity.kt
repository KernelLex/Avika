package com.avika.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.avika.app.data.repository.LocalClinicRepository
import com.avika.app.data.repository.LocalVenueRepository
import com.avika.app.navigation.AvikaNavHost
import com.avika.app.navigation.Screen
import com.avika.app.navigation.bottomNavItems
import com.avika.app.ui.components.FloatingBottomNav
import com.avika.app.ui.components.NavEntry
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

    val backStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = backStackEntry?.destination
    val onDarkHero = currentDestination?.route == Screen.Home.route

    val view = LocalView.current
    SideEffect {
        val window = (view.context as? android.app.Activity)?.window ?: return@SideEffect
        WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = !onDarkHero
    }

    Scaffold(
        bottomBar = {
            val navEntries = bottomNavItems.map { item ->
                NavEntry(
                    label = item.label,
                    icon = item.icon,
                    selected = currentDestination?.hierarchy?.any { it.route == item.screen.route } == true,
                    onClick = {
                        navController.navigate(item.screen.route) {
                            popUpTo(navController.graph.startDestinationId) { saveState = true }
                            launchSingleTop = true
                            restoreState = true
                        }
                    },
                )
            }
            FloatingBottomNav(items = navEntries)
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
