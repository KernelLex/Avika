package com.avika.app.data.local

import com.avika.app.data.model.DisabilityCategory
import com.avika.app.data.model.DisabilityCategory.ADHD
import com.avika.app.data.model.DisabilityCategory.AUTISM
import com.avika.app.data.model.DisabilityCategory.CEREBRAL_PALSY
import com.avika.app.data.model.DisabilityCategory.DOWN_SYNDROME
import com.avika.app.data.model.DisabilityCategory.HEARING_IMPAIRMENT
import com.avika.app.data.model.DisabilityCategory.INTELLECTUAL_DISABILITY
import com.avika.app.data.model.DisabilityCategory.LEARNING_DISABILITY
import com.avika.app.data.model.DisabilityCategory.NOT_SURE
import com.avika.app.data.model.DisabilityCategory.SPEECH_LANGUAGE_DELAY
import com.avika.app.data.model.DisabilityCategory.VISUAL_IMPAIRMENT
import com.avika.app.data.model.IntakeQuestion
import com.avika.app.data.model.QuestionType.MULTIPLE_CHOICE
import com.avika.app.data.model.QuestionType.SINGLE_CHOICE
import com.avika.app.data.model.QuestionType.TEXT
import com.avika.app.data.model.QuestionType.YES_NO

/**
 * Fixed question set per category — every family answers the same questions
 * for a given category, so responses stay comparable. This intentionally has
 * NO scoring, risk level, or diagnostic output: it exists to help parents
 * describe what they're observing and to route them to the right specialists
 * in the Directory. Only a qualified professional can diagnose a child.
 */
private val commonIntro = listOf(
    IntakeQuestion(
        id = "intro_when_noticed",
        prompt = "When did you first notice this?",
        type = SINGLE_CHOICE,
        options = listOf("Before 1 year old", "1–2 years old", "2–3 years old", "3–5 years old", "After 5 years old", "Just recently"),
    ),
    IntakeQuestion(
        id = "intro_seen_professional",
        prompt = "Has your child seen a doctor or therapist about this before?",
        type = YES_NO,
    ),
    IntakeQuestion(
        id = "intro_own_words",
        prompt = "In your own words, what have you noticed?",
        type = TEXT,
        helperText = "There's no wrong answer — whatever you've observed is useful.",
    ),
)

private val categoryQuestions: Map<DisabilityCategory, List<IntakeQuestion>> = mapOf(
    AUTISM to listOf(
        IntakeQuestion("autism_eye_contact", "Does your child make eye contact when you talk to them?", YES_NO),
        IntakeQuestion("autism_responds_to_name", "Does your child respond when you call their name?", YES_NO),
        IntakeQuestion("autism_points_to_share", "Does your child point at things to show you something interesting?", YES_NO),
        IntakeQuestion("autism_pretend_play", "Does your child play pretend games, like feeding a doll or talking on a toy phone?", YES_NO),
        IntakeQuestion("autism_repeated_movements", "Does your child repeat the same movement often, like hand-flapping or rocking?", YES_NO),
        IntakeQuestion("autism_sensory", "Does your child get very upset by loud sounds, bright lights, or certain textures?", YES_NO),
        IntakeQuestion("autism_routine", "Does your child like things to happen the same way every time, and get distressed by small changes?", YES_NO),
        IntakeQuestion(
            "autism_communication_level", "How does your child usually communicate?", SINGLE_CHOICE,
            options = listOf("Talks in full sentences", "Talks in single words or short phrases", "Uses gestures instead of words", "Doesn't communicate much yet"),
        ),
    ),
    CEREBRAL_PALSY to listOf(
        IntakeQuestion("cp_muscle_tone", "How would you describe your child's muscle tone compared to other children their age?", SINGLE_CHOICE, options = listOf("Stiff / tight", "Floppy / loose", "Neither noticeably")),
        IntakeQuestion("cp_gross_motor_delay", "Does your child have difficulty sitting, crawling, standing, or walking for their age?", YES_NO),
        IntakeQuestion("cp_one_sided", "Does one side of your child's body move differently than the other?", YES_NO),
        IntakeQuestion("cp_mobility_aid", "Does your child use, or has a doctor recommended, a mobility aid (walker, wheelchair, braces)?", YES_NO),
        IntakeQuestion("cp_fine_motor", "Does your child have trouble with fine movements, like holding a spoon or crayon?", YES_NO),
        IntakeQuestion("cp_birth_complications", "Were there any complications during pregnancy, birth, or shortly after that a doctor mentioned?", YES_NO),
        IntakeQuestion("cp_swallowing", "Does your child have trouble swallowing, or drool more than expected for their age?", YES_NO),
    ),
    INTELLECTUAL_DISABILITY to listOf(
        IntakeQuestion("id_milestones_later", "Is your child reaching milestones (sitting, walking, talking) later than other children their age?", YES_NO),
        IntakeQuestion("id_daily_help", "Does your child need more help than peers with everyday tasks like dressing, eating, or using the toilet?", YES_NO),
        IntakeQuestion("id_instructions", "Does your child have trouble understanding instructions that other children their age understand?", YES_NO),
        IntakeQuestion("id_school_concern", "Has a school or preschool raised concerns about your child's learning pace?", YES_NO),
        IntakeQuestion("id_problem_solving", "Does your child have trouble with simple problem-solving, like a shape puzzle, for their age?", YES_NO),
        IntakeQuestion("id_play_level", "Does your child play and interact with peers the same way as kids their age, or more like a younger child?", SINGLE_CHOICE, options = listOf("Same as peers", "More like a younger child", "Not sure")),
    ),
    ADHD to listOf(
        IntakeQuestion("adhd_sit_still", "Does your child have trouble sitting still, even when asked to?", YES_NO),
        IntakeQuestion("adhd_distracted", "Does your child get distracted very easily, even during things they enjoy?", YES_NO),
        IntakeQuestion("adhd_interrupts", "Does your child interrupt others or have trouble waiting their turn?", YES_NO),
        IntakeQuestion("adhd_impulsive", "Does your child act without thinking about consequences, like running off or grabbing without asking?", YES_NO),
        IntakeQuestion("adhd_forgetful", "Does your child lose things often or forget instructions quickly?", YES_NO),
        IntakeQuestion("adhd_teacher_flagged", "Has a teacher or caregiver mentioned your child is harder to manage than classmates?", YES_NO),
        IntakeQuestion("adhd_focus_span", "How long can your child usually focus on one activity they enjoy?", SINGLE_CHOICE, options = listOf("Under 5 minutes", "5–15 minutes", "15–30 minutes", "More than 30 minutes")),
    ),
    HEARING_IMPAIRMENT to listOf(
        IntakeQuestion("hearing_responds_name", "Does your child respond to their name from another room, without seeing you?", YES_NO),
        IntakeQuestion("hearing_volume", "Does your child turn the TV/phone volume up louder than other family members prefer?", YES_NO),
        IntakeQuestion("hearing_watches_faces", "Does your child watch faces closely, as if relying on lip-reading?", YES_NO),
        IntakeQuestion("hearing_ear_infections", "Has your child had frequent ear infections or fluid in the ears?", YES_NO),
        IntakeQuestion("hearing_newborn_screen", "Did your child pass their newborn hearing screening, if one was done?", SINGLE_CHOICE, options = listOf("Yes, passed", "No, did not pass", "Not sure / wasn't done")),
        IntakeQuestion("hearing_speech_delay", "Is your child's speech delayed compared to other children their age?", YES_NO),
    ),
    VISUAL_IMPAIRMENT to listOf(
        IntakeQuestion("vision_holds_close", "Does your child hold books, toys, or screens unusually close to their face?", YES_NO),
        IntakeQuestion("vision_bumps_into", "Does your child bump into furniture or trip more than expected for their age?", YES_NO),
        IntakeQuestion("vision_eyes_wander", "Do your child's eyes appear to cross, wander, or not track together?", YES_NO),
        IntakeQuestion("vision_squints", "Does your child squint or tilt their head to look at things?", YES_NO),
        IntakeQuestion("vision_doctor_mentioned", "Has a doctor mentioned anything about your child's eyes at a checkup?", YES_NO),
        IntakeQuestion("vision_avoids_activities", "Does your child avoid activities needing clear vision, like drawing or catching a ball?", YES_NO),
    ),
    SPEECH_LANGUAGE_DELAY to listOf(
        IntakeQuestion("speech_word_count", "How does your child mostly communicate right now?", SINGLE_CHOICE, options = listOf("No words yet", "A few single words", "Short phrases (2–3 words)", "Full sentences")),
        IntakeQuestion("speech_understands", "Does your child understand simple instructions, like \"bring me the ball\", even if they don't say much?", YES_NO),
        IntakeQuestion("speech_hard_to_understand", "Is your child's speech hard for family members to understand?", YES_NO),
        IntakeQuestion("speech_stutters", "Does your child stutter, or get stuck repeating sounds or words?", YES_NO),
        IntakeQuestion("speech_uses_gestures", "Does your child use gestures, like pointing or waving, instead of words?", YES_NO),
        IntakeQuestion("speech_others_flagged", "Has anyone else, like school or family, mentioned your child's speech seems behind?", YES_NO),
    ),
    DOWN_SYNDROME to listOf(
        IntakeQuestion("ds_diagnosis_status", "Has your child been diagnosed with Down syndrome, or is a doctor currently assessing for it?", SINGLE_CHOICE, options = listOf("Diagnosed", "Being assessed", "Not diagnosed, just want general guidance")),
        IntakeQuestion("ds_medical_flags", "Does your child have any known heart, hearing, vision, or thyroid concerns a doctor has flagged?", YES_NO),
        IntakeQuestion("ds_muscle_tone", "How would you describe your child's overall muscle tone?", SINGLE_CHOICE, options = listOf("Floppy / low tone", "Typical", "Not sure")),
        IntakeQuestion("ds_motor_milestones", "Is your child reaching movement milestones (sitting, crawling, walking) later than typical?", YES_NO),
        IntakeQuestion("ds_speech_milestones", "Is your child reaching speech and language milestones later than typical?", YES_NO),
        IntakeQuestion("ds_regular_checkups", "Does your child have regular check-ups with a pediatrician familiar with Down syndrome care?", YES_NO),
    ),
    LEARNING_DISABILITY to listOf(
        IntakeQuestion("ld_reading", "Does your child struggle specifically with reading, while doing fine in other areas?", YES_NO),
        IntakeQuestion("ld_writing", "Does your child struggle specifically with writing or spelling, while doing fine in other areas?", YES_NO),
        IntakeQuestion("ld_math", "Does your child struggle specifically with math or numbers, while doing fine in other areas?", YES_NO),
        IntakeQuestion("ld_avoids_schoolwork", "Does your child avoid or get very frustrated with schoolwork or homework?", YES_NO),
        IntakeQuestion("ld_teacher_flagged", "Has a teacher mentioned your child's reading, writing, or math level seems behind their grade?", YES_NO),
        IntakeQuestion("ld_understands_verbally", "Does your child understand concepts well when explained out loud, even if reading/writing is hard?", YES_NO),
    ),
    NOT_SURE to listOf(
        IntakeQuestion(
            "gen_concern_areas", "Which areas are you most concerned about?", MULTIPLE_CHOICE,
            options = listOf("Talking / communication", "Movement / coordination", "Learning / understanding", "Behavior / attention", "Social interaction", "Hearing", "Vision", "Something else"),
        ),
        IntakeQuestion("gen_motor_milestones", "Is your child meeting movement milestones around the same time as peers?", YES_NO),
        IntakeQuestion("gen_speech_milestones", "Is your child meeting speech and language milestones around the same time as peers?", YES_NO),
        IntakeQuestion("gen_social_play", "Does your child interact and play with other children similarly to their peers?", YES_NO),
        IntakeQuestion("gen_school_flagged", "Has your child's school or preschool shared any concerns with you?", YES_NO),
    ),
)

fun questionsFor(category: DisabilityCategory): List<IntakeQuestion> =
    commonIntro + (categoryQuestions[category] ?: emptyList())
