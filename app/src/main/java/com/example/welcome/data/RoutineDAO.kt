package com.example.welcome.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import kotlinx.coroutines.flow.Flow

@Dao
interface RoutineDAO {

    @Upsert //Upsert is a combination of Insert and Update. If the Routine already exists, it will be updated. If it doesn't exist, it will be inserted.
    suspend fun upsertRoutine(routine: Routine): Long //suspend keyword indicates that this function is asynchronous and can be called from a coroutine.

    @Delete
    suspend fun deleteRoutine(routine: Routine)

    @Query("SELECT * FROM routines ORDER BY id DESC")
    fun getAllRoutines(): Flow<List<Routine>>

    @Query("SELECT * FROM routines WHERE id = :id")
    suspend fun getRoutineById(id: Int): Routine



}