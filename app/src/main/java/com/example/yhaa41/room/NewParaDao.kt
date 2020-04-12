package com.example.yhaa41.room

import androidx.room.*

@Dao
interface NewParaDao {

    @Insert
    suspend fun addPara(para:Para)

   // @Query("SELECT * FROM para ORDER BY id DESC")
    @Query("SELECT * FROM para ORDER BY id ASC")
    suspend fun getAllParas(): List<Para>

    @Insert
    suspend fun addMultipleParas(vararg para: Para)

    @Update
    suspend fun updatePara(para:Para)

    @Delete
    suspend fun deletePara(para:Para)

    @Query("DELETE FROM para")
    suspend fun deleteAllParas()
}