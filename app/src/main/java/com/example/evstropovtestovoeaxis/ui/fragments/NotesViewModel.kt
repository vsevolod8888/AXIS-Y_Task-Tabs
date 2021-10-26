package com.example.evstropovtestovoeaxis.ui.fragments

import android.net.Uri
import androidx.lifecycle.*
import com.example.evstropovtestovoeaxis.data.database.Note
import com.example.evstropovtestovoeaxis.domain.NoteDomain
import com.example.evstropovtestovoeaxis.domain.usecases.AddDefaultNotesUseCase
import com.example.evstropovtestovoeaxis.domain.usecases.DeleteNoteUseCase
import com.example.evstropovtestovoeaxis.domain.usecases.GetNotesByEmailUseCase
import com.example.evstropovtestovoeaxis.domain.usecases.InsertNoteUseCase
import kotlinx.coroutines.launch
import javax.inject.Inject

data class UserInfo(val name: String?, val email: String?, val photo: Uri?)

class NotesFragmentViewModel @Inject constructor(private val addDefaultNotesUseCase: AddDefaultNotesUseCase,
                                                 private val deleteNoteUseCase: DeleteNoteUseCase,
                                                 private val getNotesByEmailUseCase: GetNotesByEmailUseCase,
                                                 private val insertNoteUseCase: InsertNoteUseCase) : ViewModel() {
    var user: MutableLiveData<UserInfo?> = MutableLiveData()
    fun getNotes(): LiveData<List<NoteDomain>?> {
        return getNotesByEmailUseCase.invoke(user.value?.email)
    }

    fun addDefaultNotes(){
        viewModelScope.launch {
            addDefaultNotesUseCase.invoke(user.value?.email?:"")
        }
    }

    fun insertNote(note: Note) {
        viewModelScope.launch {
            insertNoteUseCase.invoke(note)
        }
    }
    fun deleteNote(id: Long) {
        viewModelScope.launch {
            deleteNoteUseCase.invoke(id)
        }
    }
    fun saveUserInfo(personName: String?, personEmail: String?, personPhoto: Uri?) {
        user.value = UserInfo(personName,personEmail,personPhoto)
    }
}