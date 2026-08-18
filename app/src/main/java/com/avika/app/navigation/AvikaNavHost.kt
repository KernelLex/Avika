package com.avika.app.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.avika.app.data.local.seedSchemes
import com.avika.app.data.repository.ClinicRepository
import com.avika.app.data.repository.VenueRepository
import com.avika.app.session.SessionViewModel
import com.avika.app.ui.screens.about.AboutScreen
import com.avika.app.ui.screens.directory.DirectoryDetailScreen
import com.avika.app.ui.screens.directory.DirectoryListScreen
import com.avika.app.ui.screens.family.ProfileScreen
import com.avika.app.ui.screens.home.HomeScreen
import com.avika.app.ui.screens.schemes.SchemesScreen
import com.avika.app.ui.screens.venues.VenueDetailScreen
import com.avika.app.ui.screens.venues.VenueListScreen

@Composable
fun AvikaNavHost(
    navController: NavHostController,
    clinicRepository: ClinicRepository,
    venueRepository: VenueRepository,
    sessionViewModel: SessionViewModel,
    modifier: Modifier = Modifier,
) {
    NavHost(navController = navController, startDestination = Screen.Home.route, modifier = modifier) {
        composable(Screen.Home.route) {
            HomeScreen(
                onOpenDirectory = { navController.navigate(Screen.Directory.route) },
                onOpenVenues = { navController.navigate(Screen.Venues.route) },
                onOpenSchemes = { navController.navigate(Screen.Schemes.route) },
                onOpenProfile = { navController.navigate(Screen.Profile.route) },
            )
        }
        composable(Screen.Profile.route) {
            ProfileScreen(
                sessionViewModel = sessionViewModel,
                onExit = { navController.popBackStack() },
            )
        }
        composable(Screen.Directory.route) {
            DirectoryListScreen(
                clinics = clinicRepository.getAll(),
                onClinicClick = { navController.navigate(Screen.DirectoryDetail.createRoute(it.id)) },
            )
        }
        composable(
            route = Screen.DirectoryDetail.route,
            arguments = listOf(navArgument("clinicId") { type = NavType.StringType }),
        ) { backStackEntry ->
            val clinicId = backStackEntry.arguments?.getString("clinicId")
            val clinic = clinicRepository.getById(clinicId.orEmpty())
            if (clinic != null) {
                DirectoryDetailScreen(clinic = clinic, onBack = { navController.popBackStack() })
            }
        }
        composable(Screen.Venues.route) {
            VenueListScreen(
                venues = venueRepository.getAll(),
                onVenueClick = { navController.navigate(Screen.VenueDetail.createRoute(it.id)) },
            )
        }
        composable(
            route = Screen.VenueDetail.route,
            arguments = listOf(navArgument("venueId") { type = NavType.StringType }),
        ) { backStackEntry ->
            val venueId = backStackEntry.arguments?.getString("venueId")
            val venue = venueRepository.getById(venueId.orEmpty())
            if (venue != null) {
                VenueDetailScreen(venue = venue, onBack = { navController.popBackStack() })
            }
        }
        composable(Screen.Schemes.route) {
            SchemesScreen(sections = seedSchemes)
        }
        composable(Screen.About.route) {
            AboutScreen()
        }
    }
}
