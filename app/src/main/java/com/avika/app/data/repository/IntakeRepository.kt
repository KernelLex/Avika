package com.avika.app.data.repository

import com.avika.app.data.model.IntakeResponse
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await

/** families/{familyId}/children/{childId}/intake/{categoryId} -> IntakeResponse */
class IntakeRepository(private val db: FirebaseFirestore = FirebaseFirestore.getInstance()) {

    private fun intakeCollection(familyId: String, childId: String) =
        db.collection("families").document(familyId)
            .collection("children").document(childId)
            .collection("intake")

    suspend fun saveResponse(familyId: String, childId: String, response: IntakeResponse) {
        intakeCollection(familyId, childId).document(response.categoryId).set(
            mapOf(
                "categoryId" to response.categoryId,
                "answers" to response.answers,
                "status" to response.status,
                "updatedAtMillis" to System.currentTimeMillis(),
            )
        ).await()
    }

    suspend fun getResponses(familyId: String, childId: String): List<IntakeResponse> {
        val snapshot = intakeCollection(familyId, childId).get().await()
        return snapshot.documents.map { doc ->
            @Suppress("UNCHECKED_CAST")
            IntakeResponse(
                categoryId = doc.getString("categoryId") ?: doc.id,
                answers = (doc.get("answers") as? Map<String, String>) ?: emptyMap(),
                status = doc.getString("status") ?: "in_progress",
                updatedAtMillis = doc.getLong("updatedAtMillis") ?: 0L,
            )
        }
    }
}
