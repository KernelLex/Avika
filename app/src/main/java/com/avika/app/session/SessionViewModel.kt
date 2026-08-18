package com.avika.app.session

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.avika.app.data.model.ChildProfile
import com.avika.app.data.model.Family
import com.avika.app.data.model.IntakeResponse
import com.avika.app.data.repository.AuthRepository
import com.avika.app.data.repository.FamilyRepository
import com.avika.app.data.repository.IntakeRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

data class SessionUiState(
    val uid: String? = null,
    val family: Family? = null,
    val children: List<ChildProfile> = emptyList(),
    val loading: Boolean = false,
)

class SessionViewModel(
    private val authRepository: AuthRepository = AuthRepository(),
    private val familyRepository: FamilyRepository = FamilyRepository(),
    private val intakeRepository: IntakeRepository = IntakeRepository(),
) : ViewModel() {

    private val _uiState = MutableStateFlow(SessionUiState())
    val uiState: StateFlow<SessionUiState> = _uiState.asStateFlow()

    val authRepo: AuthRepository get() = authRepository

    fun refresh() {
        val uid = authRepository.currentUserId
        if (uid == null) {
            _uiState.value = SessionUiState()
            return
        }
        _uiState.value = _uiState.value.copy(uid = uid, loading = true)
        viewModelScope.launch {
            val familyId = familyRepository.getFamilyIdForUser(uid)
            val family = familyId?.let { familyRepository.getFamily(it) }
            val children = familyId?.let { familyRepository.getChildren(it) } ?: emptyList()
            _uiState.value = SessionUiState(uid = uid, family = family, children = children, loading = false)
        }
    }

    suspend fun createFamily(name: String) {
        val uid = authRepository.currentUserId ?: return
        val family = familyRepository.createFamily(uid, name)
        _uiState.value = _uiState.value.copy(family = family)
    }

    suspend fun joinFamily(inviteCode: String): Boolean {
        val uid = authRepository.currentUserId ?: return false
        val family = familyRepository.joinFamily(uid, inviteCode) ?: return false
        val children = familyRepository.getChildren(family.id)
        _uiState.value = _uiState.value.copy(family = family, children = children)
        return true
    }

    suspend fun addChild(name: String, dateOfBirth: String) {
        val familyId = _uiState.value.family?.id ?: return
        val id = familyRepository.addChild(familyId, ChildProfile(name = name, dateOfBirth = dateOfBirth))
        val updated = _uiState.value.children + ChildProfile(id = id, name = name, dateOfBirth = dateOfBirth)
        _uiState.value = _uiState.value.copy(children = updated)
    }

    suspend fun saveIntakeResponse(childId: String, response: IntakeResponse) {
        val familyId = _uiState.value.family?.id ?: return
        intakeRepository.saveResponse(familyId, childId, response)
    }

    suspend fun getIntakeResponses(childId: String): List<IntakeResponse> {
        val familyId = _uiState.value.family?.id ?: return emptyList()
        return intakeRepository.getResponses(familyId, childId)
    }

    fun signOut() {
        authRepository.signOut()
        _uiState.value = SessionUiState()
    }
}
