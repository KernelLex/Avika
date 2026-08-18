package com.avika.app.ui.screens.family

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.avika.app.data.model.ChildProfile
import com.avika.app.data.model.DisabilityCategory
import com.avika.app.session.SessionViewModel
import com.avika.app.ui.screens.auth.PhoneAuthFlow
import com.avika.app.ui.screens.intake.CategoryPickerScreen
import com.avika.app.ui.screens.intake.IntakeQuestionScreen

private sealed class ProfileSub {
    data object FamilyHome : ProfileSub()
    data object AddChild : ProfileSub()
    data class PickCategory(val child: ChildProfile) : ProfileSub()
    data class Questions(val child: ChildProfile, val category: DisabilityCategory) : ProfileSub()
}

/**
 * Orchestrates sign-in -> family setup -> add first child -> family hub / intake,
 * all as one screen so the main nav graph doesn't need a route per wizard step.
 */
@Composable
fun ProfileScreen(
    sessionViewModel: SessionViewModel,
    onExit: () -> Unit,
) {
    val uiState by sessionViewModel.uiState.collectAsState()
    var sub by remember { mutableStateOf<ProfileSub>(ProfileSub.FamilyHome) }

    LaunchedEffect(Unit) { sessionViewModel.refresh() }

    when {
        uiState.uid == null -> {
            PhoneAuthFlow(
                sessionViewModel = sessionViewModel,
                onBack = onExit,
                onAuthenticated = { sessionViewModel.refresh() },
            )
        }
        uiState.loading -> {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                CircularProgressIndicator()
            }
        }
        uiState.family == null -> {
            FamilySetupFlow(
                sessionViewModel = sessionViewModel,
                onBack = onExit,
                onFamilyReady = { sessionViewModel.refresh() },
            )
        }
        uiState.children.isEmpty() && sub !is ProfileSub.AddChild -> {
            AddChildScreen(
                sessionViewModel = sessionViewModel,
                onBack = onExit,
                onChildAdded = { sessionViewModel.refresh() },
            )
        }
        else -> when (val current = sub) {
            is ProfileSub.FamilyHome -> FamilyHomeScreen(
                family = uiState.family!!,
                children = uiState.children,
                onBack = onExit,
                onAddChild = { sub = ProfileSub.AddChild },
                onOpenChildIntake = { child -> sub = ProfileSub.PickCategory(child) },
                onSignOut = {
                    sessionViewModel.signOut()
                    onExit()
                },
            )
            is ProfileSub.AddChild -> AddChildScreen(
                sessionViewModel = sessionViewModel,
                onBack = { sub = ProfileSub.FamilyHome },
                onChildAdded = {
                    sessionViewModel.refresh()
                    sub = ProfileSub.FamilyHome
                },
            )
            is ProfileSub.PickCategory -> CategoryPickerScreen(
                child = current.child,
                onBack = { sub = ProfileSub.FamilyHome },
                onCategoryChosen = { category -> sub = ProfileSub.Questions(current.child, category) },
            )
            is ProfileSub.Questions -> IntakeQuestionScreen(
                sessionViewModel = sessionViewModel,
                child = current.child,
                category = current.category,
                onBack = { sub = ProfileSub.PickCategory(current.child) },
                onCompleted = { sub = ProfileSub.FamilyHome },
            )
        }
    }
}
