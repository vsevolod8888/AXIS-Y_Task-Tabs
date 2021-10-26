package com.example.evstropovtestovoeaxis.data.repozitory

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations

import com.example.evstropovtestovoeaxis.data.database.DatabaseNote
import com.example.evstropovtestovoeaxis.data.database.Note
import com.example.evstropovtestovoeaxis.data.database.asDomainNoteModel
import com.example.evstropovtestovoeaxis.domain.NoteDomain
import com.example.evstropovtestovoeaxis.domain.Repozitory

class RepozitoryImpl (val database: DatabaseNote):Repozitory{

    override suspend fun inserNote(note: Note) {
        database.noteDao.insert(note)
    }
    override suspend fun checkCountOfNotes(email: String): Int {
        return database.noteDao.getCountNumberOfNotes(email)
    }
    override suspend fun deleteNote(id: Long) {
        database.noteDao.delete(id)
    }
    override fun getListOfNotesByEmail(email: String):LiveData<List<NoteDomain>?> = Transformations.map(database.noteDao.getByEmail(email)){
        it?.asDomainNoteModel()
    }
}
