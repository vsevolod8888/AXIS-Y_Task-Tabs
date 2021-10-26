package com.example.evstropovtestovoeaxis.domain

import androidx.lifecycle.LiveData
import com.example.evstropovtestovoeaxis.data.database.Note

interface Repozitory {
    suspend fun inserNote(note: Note)
    suspend fun checkCountOfNotes(email: String): Int
    suspend fun deleteNote(id: Long)
    fun getListOfNotesByEmail(email: String): LiveData<List<NoteDomain>?>
}