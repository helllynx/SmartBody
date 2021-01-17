package org.helllynx.smartbody.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Immutable model class for a Task. In order to compile with Room, we can't use @JvmOverloads to
 * generate multiple constructors.
 *
 * @param title title of the task
 * @param description description of the task
 * @param id id of the task
 */
@Entity(tableName = "exercise")
data class Exercise @JvmOverloads constructor(
        @PrimaryKey(autoGenerate = true) var id: Long,
        @ColumnInfo(name = "title") var title: String,
        @ColumnInfo(name = "description") var description: String = "",
        @ColumnInfo(name = "type") var type: ExerciseType,
) {

    val titleForList: String
        get() = title

    val typeForList: String
        get() = type.name

    val descriptionForList: String
        get() = description
}
