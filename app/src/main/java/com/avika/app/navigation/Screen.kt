package com.avika.app.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.MenuBook
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.LocalHospital
import androidx.compose.material.icons.filled.Park
import androidx.compose.ui.graphics.vector.ImageVector

sealed class Screen(val route: String) {
    data object Home : Screen("home")
    data object Directory : Screen("directory")
    data object DirectoryDetail : Screen("directory/{clinicId}") {
        fun createRoute(clinicId: String) = "directory/$clinicId"
    }
    data object Venues : Screen("venues")
    data object VenueDetail : Screen("venues/{venueId}") {
        fun createRoute(venueId: String) = "venues/$venueId"
    }
    data object Schemes : Screen("schemes")
    data object About : Screen("about")
    data object Profile : Screen("profile")
}

data class BottomNavItem(
    val screen: Screen,
    val label: String,
    val icon: ImageVector,
)

val bottomNavItems = listOf(
    BottomNavItem(Screen.Home, "Home", Icons.Filled.Home),
    BottomNavItem(Screen.Directory, "Clinics", Icons.Filled.LocalHospital),
    BottomNavItem(Screen.Venues, "Venues", Icons.Filled.Park),
    BottomNavItem(Screen.Schemes, "Schemes", Icons.AutoMirrored.Filled.MenuBook),
    BottomNavItem(Screen.About, "About", Icons.Filled.Info),
)
