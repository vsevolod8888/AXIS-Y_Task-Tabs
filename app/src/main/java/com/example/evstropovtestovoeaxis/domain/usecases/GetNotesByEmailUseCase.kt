package com.example.evstropovtestovoeaxis.domain.usecases

import androidx.lifecycle.LiveData
import com.example.evstropovtestovoeaxis.domain.NoteDomain
import com.example.evstropovtestovoeaxis.domain.Repozitory
import javax.inject.Inject

class GetNotesByEmailUseCase @Inject constructor(private val repozitory: Repozitory) {
    fun invoke(email:String?): LiveData<List<NoteDomain>?> {
      return  repozitory.getListOfNotesByEmail(email?:"")
    }
}