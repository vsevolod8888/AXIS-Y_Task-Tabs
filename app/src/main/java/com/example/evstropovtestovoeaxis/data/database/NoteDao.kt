package com.example.evstropovtestovoeaxis.data.database

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface NoteDao {
    @Query("SELECT * FROM notes ")
    fun getAll(): LiveData<List<Note>?>

    @Query("SELECT * FROM notes WHERE email = :email")
    fun getByEmail(email: String?): LiveData<List<Note>?>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun  insert(n: Note?)

    @Query("DELETE FROM notes WHERE id = :id")
    suspend fun delete(id: Long)

    @Query("SELECT COUNT (*) FROM notes WHERE email = :email")
    suspend fun getCountNumberOfNotes(email: String?): Int

}