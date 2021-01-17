package org.helllynx.smartbody.database.entity

enum class Gender {
    MALE, FEMALE;

    companion object {
        fun getGenderById(id: Int): Gender {
            return Gender.values()[id]
        }
    }
}