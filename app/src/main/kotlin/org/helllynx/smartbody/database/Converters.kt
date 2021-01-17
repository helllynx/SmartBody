package org.helllynx.smartbody.database

import androidx.room.TypeConverter
import org.helllynx.smartbody.database.entity.ExerciseType

@Suppress("TooManyFunctions")
object Converters {

    @TypeConverter
    @JvmStatic
    fun exerciseTypeToString(value: ExerciseType): String = value.name

    @TypeConverter
    @JvmStatic
    fun stringToExerciseType(value: String): ExerciseType = ExerciseType.valueOf(value)
}
