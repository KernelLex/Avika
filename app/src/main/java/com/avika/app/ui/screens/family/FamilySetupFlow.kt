package com.avika.app.ui.screens.family

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.avika.app.session.SessionViewModel
import com.avika.app.ui.components.AvikaTopBar
import kotlinx.coroutines.launch

private enum class SetupStep { CHOOSE, CREATE, JOIN }

@Composable
fun FamilySetupFlow(
    sessionViewModel: SessionViewModel,
    onBack: () -> Unit,
    onFamilyReady: () -> Unit,
) {
    var step by remember { mutableStateOf(SetupStep.CHOOSE) }
    var familyName by remember { mutableStateOf("") }
    var inviteCode by remember { mutableStateOf("") }
    var isLoading by remember { mutableStateOf(false) }
    var errorMessage by remember { mutableStateOf<String?>(null) }
    val scope = rememberCoroutineScope()

    Scaffold(
        topBar = {
            AvikaTopBar(
                title = "Set up your family",
                onBack = { if (step == SetupStep.CHOOSE) onBack() else step = SetupStep.CHOOSE },
            )
        },
    ) { padding ->
        Column(
            modifier = Modifier.fillMaxSize().padding(padding).padding(horizontal = 20.dp, vertical = 12.dp),
            verticalArrangement = Arrangement.spacedBy(14.dp),
        ) {
            when (step) {
                SetupStep.CHOOSE -> {
                    Text(
                        "A family is shared by parents and siblings so everyone sees the same child's information.",
                        style = MaterialTheme.typography.bodyLarge,
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                    )
                    Card(colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)) {
                        Column(modifier = Modifier.padding(18.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
                            Text("Start a new family", style = MaterialTheme.typography.titleMedium)
                            Text(
                                "You'll get an invite code to share with your partner or other family members.",
                                style = MaterialTheme.typography.bodyMedium,
                                color = MaterialTheme.colorScheme.onSurfaceVariant,
                            )
                            Button(onClick = { step = SetupStep.CREATE }, modifier = Modifier.fillMaxWidth()) {
                                Text("Create family")
                            }
                        }
                    }
                    Card(colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)) {
                        Column(modifier = Modifier.padding(18.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
                            Text("Join with an invite code", style = MaterialTheme.typography.titleMedium)
                            Text(
                                "Ask the family member who set up Avika for their 6-character code.",
                                style = MaterialTheme.typography.bodyMedium,
                                color = MaterialTheme.colorScheme.onSurfaceVariant,
                            )
                            OutlinedButton(onClick = { step = SetupStep.JOIN }, modifier = Modifier.fillMaxWidth()) {
                                Text("Join family")
                            }
                        }
                    }
                }
                SetupStep.CREATE -> {
                    OutlinedTextField(
                        value = familyName,
                        onValueChange = { familyName = it },
                        label = { Text("Family name, e.g. \"The Sharmas\"") },
                        singleLine = true,
                        modifier = Modifier.fillMaxWidth(),
                    )
                    if (errorMessage != null) Text(errorMessage!!, color = MaterialTheme.colorScheme.error)
                    Button(
                        onClick = {
                            isLoading = true
                            scope.launch {
                                try {
                                    sessionViewModel.createFamily(familyName.trim())
                                    onFamilyReady()
                                } catch (e: Exception) {
                                    errorMessage = e.message ?: "Couldn't create the family, try again"
                                } finally {
                                    isLoading = false
                                }
                            }
                        },
                        enabled = familyName.isNotBlank() && !isLoading,
                    ) {
                        if (isLoading) CircularProgressIndicator(modifier = Modifier.padding(end = 8.dp))
                        Text("Create")
                    }
                }
                SetupStep.JOIN -> {
                    OutlinedTextField(
                        value = inviteCode,
                        onValueChange = { if (it.length <= 6) inviteCode = it.uppercase() },
                        label = { Text("6-character invite code") },
                        singleLine = true,
                        modifier = Modifier.fillMaxWidth(),
                    )
                    if (errorMessage != null) Text(errorMessage!!, color = MaterialTheme.colorScheme.error)
                    Button(
                        onClick = {
                            isLoading = true
                            scope.launch {
                                val joined = sessionViewModel.joinFamily(inviteCode)
                                isLoading = false
                                if (joined) onFamilyReady() else errorMessage = "That code didn't match a family — check it and try again"
                            }
                        },
                        enabled = inviteCode.length == 6 && !isLoading,
                    ) {
                        if (isLoading) CircularProgressIndicator(modifier = Modifier.padding(end = 8.dp))
                        Text("Join")
                    }
                }
            }
        }
    }
}
