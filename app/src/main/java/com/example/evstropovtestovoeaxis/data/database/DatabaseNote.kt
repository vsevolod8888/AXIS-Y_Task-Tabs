package com.example.evstropovtestovoeaxis.data.database
import androidx.room.Database
import androidx.room.RoomDatabase


@Database(entities = [Note::class], version = 1)
abstract class DatabaseNote : RoomDatabase(){
    abstract val noteDao: NoteDao
}

