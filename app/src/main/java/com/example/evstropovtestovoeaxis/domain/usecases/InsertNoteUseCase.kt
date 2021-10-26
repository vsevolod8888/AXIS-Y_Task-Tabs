package com.example.evstropovtestovoeaxis.domain.usecases

import com.example.evstropovtestovoeaxis.data.database.Note
import com.example.evstropovtestovoeaxis.domain.Repozitory
import javax.inject.Inject

class InsertNoteUseCase @Inject constructor(private val repozitory: Repozitory) {
   suspend fun invoke(note: Note){
        repozitory.inserNote(note)
    }
}