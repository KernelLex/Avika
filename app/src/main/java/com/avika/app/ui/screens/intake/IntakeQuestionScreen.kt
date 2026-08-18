package com.avika.app.ui.screens.intake

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.foundation.selection.toggleable
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.RadioButton
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
import com.avika.app.data.local.questionsFor
import com.avika.app.data.model.ChildProfile
import com.avika.app.data.model.DisabilityCategory
import com.avika.app.data.model.IntakeResponse
import com.avika.app.data.model.QuestionType
import com.avika.app.session.SessionViewModel
import com.avika.app.ui.components.AvikaTopBar
import kotlinx.coroutines.launch

@Composable
fun IntakeQuestionScreen(
    sessionViewModel: SessionViewModel,
    child: ChildProfile,
    category: DisabilityCategory,
    onBack: () -> Unit,
    onCompleted: () -> Unit,
) {
    val questions = remember(category) { questionsFor(category) }
    var index by remember { mutableStateOf(0) }
    val answers = remember { mutableStateOf(mutableMapOf<String, String>()) }
    var isSaving by remember { mutableStateOf(false) }
    val scope = rememberCoroutineScope()

    val question = questions.getOrNull(index)

    Scaffold(
        topBar = {
            AvikaTopBar(
                title = category.label,
                onBack = { if (index == 0) onBack() else index-- },
            )
        },
    ) { padding ->
        if (question == null) {
            Column(
                modifier = Modifier.fillMaxSize().padding(padding).padding(20.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp),
            ) {
                Text("All done — thank you", style = MaterialTheme.typography.headlineMedium)
                Text(
                    "This is saved to ${child.name}'s profile. It's not a diagnosis, but it'll help you and any specialist you see get on the same page faster.",
                    style = MaterialTheme.typography.bodyLarge,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                )
                Button(onClick = onCompleted) { Text("Done") }
            }
            return@Scaffold
        }

        Column(
            modifier = Modifier.fillMaxSize().padding(padding).padding(horizontal = 20.dp, vertical = 12.dp),
            verticalArrangement = Arrangement.spacedBy(18.dp),
        ) {
            LinearProgressIndicator(
                progress = { (index + 1f) / questions.size },
                modifier = Modifier.fillMaxWidth(),
            )
            Text(question.prompt, style = MaterialTheme.typography.headlineMedium)
            if (question.helperText != null) {
                Text(question.helperText, style = MaterialTheme.typography.bodyMedium, color = MaterialTheme.colorScheme.onSurfaceVariant)
            }

            val currentAnswer = answers.value[question.id]

            when (question.type) {
                QuestionType.YES_NO -> {
                    Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                        listOf("Yes", "No").forEach { option ->
                            val selected = currentAnswer == option
                            if (selected) {
                                Button(onClick = { setAnswer(answers, question.id, option) }, modifier = Modifier.weight(1f)) { Text(option) }
                            } else {
                                OutlinedButton(onClick = { setAnswer(answers, question.id, option) }, modifier = Modifier.weight(1f)) { Text(option) }
                            }
                        }
                    }
                }
                QuestionType.SINGLE_CHOICE -> {
                    Column(Modifier.selectableGroup()) {
                        question.options.forEach { option ->
                            val selected = currentAnswer == option
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .selectable(selected = selected, onClick = { setAnswer(answers, question.id, option) })
                                    .padding(vertical = 8.dp),
                            ) {
                                RadioButton(selected = selected, onClick = { setAnswer(answers, question.id, option) })
                                Text(option, style = MaterialTheme.typography.bodyLarge, modifier = Modifier.padding(start = 8.dp, top = 12.dp))
                            }
                        }
                    }
                }
                QuestionType.MULTIPLE_CHOICE -> {
                    val chosen = currentAnswer?.split("|")?.toMutableSet() ?: mutableSetOf()
                    Column {
                        question.options.forEach { option ->
                            val checked = option in chosen
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .toggleable(
                                        value = checked,
                                        onValueChange = { isChecked ->
                                            val updated = chosen.toMutableSet()
                                            if (isChecked) updated.add(option) else updated.remove(option)
                                            setAnswer(answers, question.id, updated.joinToString("|"))
                                        },
                                    )
                                    .padding(vertical = 8.dp),
                            ) {
                                Checkbox(checked = checked, onCheckedChange = null)
                                Text(option, style = MaterialTheme.typography.bodyLarge, modifier = Modifier.padding(start = 8.dp, top = 12.dp))
                            }
                        }
                    }
                }
                QuestionType.TEXT -> {
                    OutlinedTextField(
                        value = currentAnswer ?: "",
                        onValueChange = { setAnswer(answers, question.id, it) },
                        minLines = 3,
                        modifier = Modifier.fillMaxWidth(),
                    )
                }
            }

            Spacer(modifier = Modifier.weight(1f))

            val canAdvance = question.type == QuestionType.TEXT || !currentAnswer.isNullOrBlank()
            Button(
                onClick = {
                    if (index == questions.lastIndex) {
                        isSaving = true
                        scope.launch {
                            sessionViewModel.saveIntakeResponse(
                                child.id,
                                IntakeResponse(categoryId = category.name, answers = answers.value.toMap(), status = "completed"),
                            )
                            isSaving = false
                            index++
                        }
                    } else {
                        index++
                    }
                },
                enabled = canAdvance && !isSaving,
                modifier = Modifier.fillMaxWidth().height(52.dp),
            ) {
                if (isSaving) {
                    CircularProgressIndicator(modifier = Modifier.padding(end = 8.dp))
                } else {
                    Text(if (index == questions.lastIndex) "Finish" else "Next")
                }
            }
        }
    }
}

private fun setAnswer(state: androidx.compose.runtime.MutableState<MutableMap<String, String>>, questionId: String, value: String) {
    val updated = state.value.toMutableMap()
    updated[questionId] = value
    state.value = updated
}
