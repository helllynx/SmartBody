package org.helllynx.smartbody.di

import android.content.Context
import android.content.SharedPreferences
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.Dispatchers
import org.helllynx.smartbody.database.SmartBodyDatabase
import org.helllynx.smartbody.repository.SettingsRepository
import javax.inject.Singleton


@Module
@InstallIn(ApplicationComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideDataBase(@ApplicationContext context: Context): SmartBodyDatabase {
        return Room.databaseBuilder(
                context.applicationContext,
                SmartBodyDatabase::class.java,
                "SmartBody.db"
        ).build()
    }

    @Singleton
    @Provides
    fun provideIoDispatcher() = Dispatchers.IO

    @Singleton
    @Provides
    fun provideSharedPreferences(@ApplicationContext context: Context): SharedPreferences {
        return context.getSharedPreferences("smart_body", Context.MODE_PRIVATE)
    }
}

@Module
@InstallIn(ApplicationComponent::class)
object RepositoryModule {

    @Singleton
    @Provides
    fun provideTasksRepository(
            sharedPreferences: SharedPreferences
    ): SettingsRepository {
        return SettingsRepository(sharedPreferences)
    }
}
