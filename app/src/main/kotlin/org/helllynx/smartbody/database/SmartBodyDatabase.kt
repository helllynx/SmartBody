package org.helllynx.smartbody.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import org.helllynx.smartbody.database.dao.ExerciseDao
import org.helllynx.smartbody.database.entity.Exercise

@Database(entities = [Exercise::class], version = 1, exportSchema = false)
@TypeConverters(Converters::class)
abstract class SmartBodyDatabase : RoomDatabase() {

    abstract fun exerciseDao(): ExerciseDao
}
