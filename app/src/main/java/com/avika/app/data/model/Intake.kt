package com.avika.app.data.model

/**
 * Broad, non-diagnostic categories a parent can pick to describe what they're
 * seeing in their child. Selecting one loads that category's question bank
 * (see IntakeQuestionBank) — the same fixed set every family sees for that
 * category, so answers stay comparable across families over time.
 */
enum class DisabilityCategory(val label: String, val description: String) {
    AUTISM("Autism Spectrum", "Social communication, repetitive behaviors, sensory sensitivities"),
    CEREBRAL_PALSY("Cerebral Palsy", "Movement, muscle tone, coordination"),
    INTELLECTUAL_DISABILITY("Intellectual Disability", "Learning pace, adaptive daily-living skills"),
    ADHD("ADHD / Attention", "Attention, impulsivity, activity level"),
    HEARING_IMPAIRMENT("Hearing Impairment", "Hearing loss, communication mode"),
    VISUAL_IMPAIRMENT("Visual Impairment", "Vision loss, mobility"),
    SPEECH_LANGUAGE_DELAY("Speech & Language Delay", "Talking, understanding language"),
    DOWN_SYNDROME("Down Syndrome", "General development and health"),
    LEARNING_DISABILITY("Learning Disability", "Reading, writing, or math specifically"),
    NOT_SURE("Not Sure Yet", "General screening across all areas"),
}

enum class QuestionType { YES_NO, MULTIPLE_CHOICE, SINGLE_CHOICE, TEXT }

data class IntakeQuestion(
    val id: String,
    val prompt: String,
    val type: QuestionType,
    val options: List<String> = emptyList(),
    val helperText: String? = null,
)

/**
 * One family's answers for one child, for one category. A child can have
 * multiple of these over time (e.g. autism concerns now, ADHD questions added
 * later) — each is its own document keyed by categoryId.
 */
data class IntakeResponse(
    val categoryId: String = "",
    val answers: Map<String, String> = emptyMap(),
    val status: String = "in_progress", // "in_progress" | "completed"
    val updatedAtMillis: Long = 0L,
)
