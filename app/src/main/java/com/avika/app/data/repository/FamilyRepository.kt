package com.avika.app.data.repository

import com.avika.app.data.model.ChildProfile
import com.avika.app.data.model.Family
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await

/**
 * Firestore layout:
 *   users/{uid}                        -> { familyId }
 *   families/{familyId}                -> { name, inviteCode, memberUids[] }
 *   families/{familyId}/children/{id}  -> { name, dateOfBirth }
 */
class FamilyRepository(private val db: FirebaseFirestore = FirebaseFirestore.getInstance()) {

    private fun families() = db.collection("families")
    private fun users() = db.collection("users")

    suspend fun getFamilyIdForUser(uid: String): String? =
        users().document(uid).get().await().getString("familyId")

    suspend fun createFamily(uid: String, familyName: String): Family {
        val inviteCode = generateInviteCode()
        val ref = families().document()
        val family = Family(id = ref.id, name = familyName, inviteCode = inviteCode, memberUids = listOf(uid))
        ref.set(
            mapOf(
                "name" to family.name,
                "inviteCode" to family.inviteCode,
                "memberUids" to family.memberUids,
            )
        ).await()
        users().document(uid).set(mapOf("familyId" to family.id)).await()
        return family
    }

    suspend fun joinFamily(uid: String, inviteCode: String): Family? {
        val snapshot = families().whereEqualTo("inviteCode", inviteCode.trim().uppercase()).limit(1).get().await()
        val doc = snapshot.documents.firstOrNull() ?: return null
        @Suppress("UNCHECKED_CAST")
        val existingMembers = (doc.get("memberUids") as? List<String>) ?: emptyList()
        val updatedMembers = if (uid in existingMembers) existingMembers else existingMembers + uid
        families().document(doc.id).update("memberUids", updatedMembers).await()
        users().document(uid).set(mapOf("familyId" to doc.id)).await()
        return Family(
            id = doc.id,
            name = doc.getString("name") ?: "",
            inviteCode = doc.getString("inviteCode") ?: "",
            memberUids = updatedMembers,
        )
    }

    suspend fun getFamily(familyId: String): Family? {
        val doc = families().document(familyId).get().await()
        if (!doc.exists()) return null
        @Suppress("UNCHECKED_CAST")
        return Family(
            id = doc.id,
            name = doc.getString("name") ?: "",
            inviteCode = doc.getString("inviteCode") ?: "",
            memberUids = (doc.get("memberUids") as? List<String>) ?: emptyList(),
        )
    }

    suspend fun addChild(familyId: String, child: ChildProfile): String {
        val ref = families().document(familyId).collection("children").document()
        ref.set(mapOf("name" to child.name, "dateOfBirth" to child.dateOfBirth)).await()
        return ref.id
    }

    suspend fun getChildren(familyId: String): List<ChildProfile> {
        val snapshot = families().document(familyId).collection("children").get().await()
        return snapshot.documents.map {
            ChildProfile(id = it.id, name = it.getString("name") ?: "", dateOfBirth = it.getString("dateOfBirth") ?: "")
        }
    }

    private fun generateInviteCode(): String {
        val chars = "ABCDEFGHJKLMNPQRSTUVWXYZ23456789" // no ambiguous-looking characters
        return (1..6).map { chars.random() }.joinToString("")
    }
}
