package org.helllynx.smartbody.database.entity

enum class ExerciseType {
    Reps,
    WeightRepeats,
    Time,
    WeightTime,
    Distance;

    companion object {
        fun getTypeById(id: Int) = values()[id]
    }
}