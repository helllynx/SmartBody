package org.helllynx.smartbody.repository

import android.content.SharedPreferences
import org.helllynx.smartbody.database.entity.Gender

class SettingsRepository(
        private val sharedPreferences: SharedPreferences
) {
    fun setAge(age: Int) {
        sharedPreferences.edit().putInt("age", age).apply()
    }

    fun getAge() = sharedPreferences.getInt("age", 0)

    fun setHeight(height: Int) {
        sharedPreferences.edit().putInt("height", height).apply()
    }

    fun getHeight() = sharedPreferences.getInt("height", 0)

    fun setGender(gender: Gender) {
        sharedPreferences.edit().putInt("gender", gender.ordinal).apply()
    }

    fun getGender() = Gender.getGenderById(sharedPreferences.getInt("gender", 0))
}
