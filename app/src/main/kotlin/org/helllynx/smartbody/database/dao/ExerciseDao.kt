package org.helllynx.smartbody.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import org.helllynx.smartbody.database.entity.Exercise

@Dao
interface ExerciseDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addExercise(note: Exercise)

    @Query("SELECT * FROM Exercise ORDER BY id DESC")
    fun getExercises(): Flow<List<Exercise>>

    @Query("DELETE FROM Exercise WHERE id = :id")
    suspend fun deleteExerciseById(id: Long)

    @Query("SELECT * FROM Exercise WHERE id = :id")
    suspend fun getExerciseById(id: Long): Exercise
}
