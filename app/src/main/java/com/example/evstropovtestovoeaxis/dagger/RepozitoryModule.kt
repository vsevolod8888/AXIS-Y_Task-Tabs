package com.example.evstropovtestovoeaxis.dagger

import android.content.Context
import androidx.room.Room
import com.example.evstropovtestovoeaxis.data.database.DatabaseNote
import com.example.evstropovtestovoeaxis.domain.Repozitory
import com.example.evstropovtestovoeaxis.data.repozitory.RepozitoryImpl

import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RepozitoryModule(var context: Context) {
    @Provides
    @Singleton
    fun provideContext(): Context {
        return context
    }

    @Provides
    @Singleton
    fun provideDatabase(context: Context): DatabaseNote {
        val INSTANCE = Room.databaseBuilder(
            context.applicationContext,
            DatabaseNote::class.java,
            "notes"
        ).fallbackToDestructiveMigration().build()
        return INSTANCE
    }

    @Provides
    @Singleton
    fun providesRepozitory(db: DatabaseNote): Repozitory {
        return RepozitoryImpl(db )
    }
}