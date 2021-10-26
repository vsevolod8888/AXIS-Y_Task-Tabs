package com.example.evstropovtestovoeaxis.domain.usecases

import com.example.evstropovtestovoeaxis.data.database.Note
import com.example.evstropovtestovoeaxis.domain.Repozitory
import javax.inject.Inject

class AddDefaultNotesUseCase @Inject constructor(private val repozitory: Repozitory) {
    suspend fun invoke(email:String){
        when (repozitory.checkCountOfNotes(email)) {
            0 -> {
                for (i in 0..2) {
                    email?.let {
                        val newNote = Note(
                            id = 0,
                            email = it,
                            tittle = "Блокнот номер $i",
                            content = "Блокнот по умолчанию номер $i пользователя с емэйлом $it"
                        )
                        repozitory.inserNote(newNote)
                    }
                }
            }
            1 -> for (i in 0..1) {
                email?.let {
                    val newNote = Note(
                        id = 0,
                        email = it,
                        tittle = "Блокнот номер $i",
                        content = "Блокнот по умолчанию номер $i пользователя с емэйлом $it"
                    )
                    repozitory.inserNote(newNote)
                }
            }
            2 -> email?.let {
                val newNote = Note(
                    id = 0,
                    email = it,
                    tittle = "Блокнот номер 3",
                    content = "Блокнот по умолчанию номер 3 пользователя с емэйлом $it"
                )
                repozitory.inserNote(newNote)
            }
        }
    }
}