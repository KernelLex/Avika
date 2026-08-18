package com.avika.app.ui.screens.auth

import android.app.Activity
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.avika.app.session.SessionViewModel
import com.avika.app.ui.components.AvikaTopBar
import kotlinx.coroutines.launch

private enum class AuthStep { PHONE, OTP }

@Composable
fun PhoneAuthFlow(
    sessionViewModel: SessionViewModel,
    onBack: () -> Unit,
    onAuthenticated: () -> Unit,
) {
    var step by remember { mutableStateOf(AuthStep.PHONE) }
    var phoneDigits by remember { mutableStateOf("") }
    var verificationId by remember { mutableStateOf<String?>(null) }
    var otpCode by remember { mutableStateOf("") }
    var isLoading by remember { mutableStateOf(false) }
    var errorMessage by remember { mutableStateOf<String?>(null) }

    val context = LocalContext.current
    val scope = rememberCoroutineScope()

    Scaffold(
        topBar = {
            AvikaTopBar(
                title = if (step == AuthStep.PHONE) "Sign in" else "Enter the code",
                onBack = { if (step == AuthStep.OTP) step = AuthStep.PHONE else onBack() },
            )
        },
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(horizontal = 20.dp, vertical = 12.dp),
            verticalArrangement = Arrangement.spacedBy(14.dp),
        ) {
            when (step) {
                AuthStep.PHONE -> {
                    Text(
                        "We'll text a one-time code to verify it's you. Your family members will use their own numbers to join.",
                        style = MaterialTheme.typography.bodyLarge,
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                    )
                    OutlinedTextField(
                        value = phoneDigits,
                        onValueChange = { if (it.length <= 10 && it.all(Char::isDigit)) phoneDigits = it },
                        label = { Text("Phone number") },
                        prefix = { Text("+91 ") },
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone),
                        singleLine = true,
                        modifier = Modifier.fillMaxWidth(),
                    )
                    if (errorMessage != null) {
                        Text(errorMessage!!, color = MaterialTheme.colorScheme.error, style = MaterialTheme.typography.bodyMedium)
                    }
                    Button(
                        onClick = {
                            errorMessage = null
                            isLoading = true
                            val activity = context as Activity
                            sessionViewModel.authRepo.sendOtp(
                                phoneNumber = "+91$phoneDigits",
                                activity = activity,
                                onCodeSent = { id ->
                                    isLoading = false
                                    verificationId = id
                                    step = AuthStep.OTP
                                },
                                onAutoVerified = {
                                    isLoading = false
                                    sessionViewModel.refresh()
                                    onAuthenticated()
                                },
                                onError = {
                                    isLoading = false
                                    errorMessage = it
                                },
                            )
                        },
                        enabled = phoneDigits.length == 10 && !isLoading,
                    ) {
                        if (isLoading) CircularProgressIndicator(modifier = Modifier.padding(end = 8.dp))
                        Text("Send code")
                    }
                }
                AuthStep.OTP -> {
                    Text(
                        "Enter the 6-digit code sent to +91 $phoneDigits",
                        style = MaterialTheme.typography.bodyLarge,
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                    )
                    OutlinedTextField(
                        value = otpCode,
                        onValueChange = { if (it.length <= 6 && it.all(Char::isDigit)) otpCode = it },
                        label = { Text("Code") },
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.NumberPassword),
                        singleLine = true,
                        modifier = Modifier.fillMaxWidth(),
                    )
                    if (errorMessage != null) {
                        Text(errorMessage!!, color = MaterialTheme.colorScheme.error, style = MaterialTheme.typography.bodyMedium)
                    }
                    Button(
                        onClick = {
                            val id = verificationId ?: return@Button
                            errorMessage = null
                            isLoading = true
                            scope.launch {
                                val result = sessionViewModel.authRepo.verifyCode(id, otpCode)
                                isLoading = false
                                result.onSuccess {
                                    sessionViewModel.refresh()
                                    onAuthenticated()
                                }.onFailure {
                                    errorMessage = it.message ?: "That code didn't work — try again"
                                }
                            }
                        },
                        enabled = otpCode.length == 6 && !isLoading,
                    ) {
                        if (isLoading) CircularProgressIndicator(modifier = Modifier.padding(end = 8.dp))
                        Text("Verify")
                    }
                    TextButton(onClick = { step = AuthStep.PHONE }) {
                        Text("Wrong number? Go back")
                    }
                }
            }
        }
    }
}
