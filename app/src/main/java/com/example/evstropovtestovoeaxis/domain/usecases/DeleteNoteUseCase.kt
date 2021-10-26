package com.example.evstropovtestovoeaxis.domain.usecases

import com.example.evstropovtestovoeaxis.domain.Repozitory
import javax.inject.Inject

class DeleteNoteUseCase @Inject constructor(private val repozitory: Repozitory) {
    suspend fun invoke(id:Long){
        repozitory.deleteNote(id)
    }
}