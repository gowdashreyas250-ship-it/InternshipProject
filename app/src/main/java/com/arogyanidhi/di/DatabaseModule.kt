package com.arogyanidhi.di

import android.content.Context
import androidx.room.Room
import com.arogyanidhi.data.local.ArogyaNidhiDatabase
import com.arogyanidhi.data.local.dao.HospitalDao
import com.arogyanidhi.data.local.dao.SchemeDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): ArogyaNidhiDatabase {
        return Room.databaseBuilder(
            context,
            ArogyaNidhiDatabase::class.java,
            "arogyanidhi_db"
        ).build()
    }

    @Provides
    fun provideSchemeDao(database: ArogyaNidhiDatabase): SchemeDao {
        return database.schemeDao()
    }

    @Provides
    fun provideHospitalDao(database: ArogyaNidhiDatabase): HospitalDao {
        return database.hospitalDao()
    }
}
