package com.example.yhaa41.room

import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

interface ParaDao {
    @Insert
    suspend fun addNote(para:Para)

    @Query("SELECT * FROM para ORDER BY id DESC")
    suspend fun getAllNotes(): List<Para>

    @Insert
    suspend fun addMultipleNotes(vararg para: Para)

    @Update
    suspend fun updateNote(para:Para)

    @Delete
    suspend fun deleteNote(para:Para)
}