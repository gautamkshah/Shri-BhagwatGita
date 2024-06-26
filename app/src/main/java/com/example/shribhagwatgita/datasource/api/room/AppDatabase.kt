package com.example.shribhagwatgita.datasource.api.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters







@Database(entities = [SavedChapters::class, SavedVerses::class], version = 2, exportSchema = false)
@TypeConverters(AppTypeConverters::class)
abstract class AppDatabase: RoomDatabase() {
    abstract fun savedChaptersDao(): SavedChaptersDao
    abstract fun savedVersesDao(): SavedVersesDao
    companion object{
        @Volatile
        var INSTANCE: AppDatabase? = null

        fun getDatabaseInatance(context: Context): AppDatabase{
            val tempInstance = INSTANCE
            if(tempInstance != null){
                return tempInstance
            }
            synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "AppDatabase"
                ).fallbackToDestructiveMigration().build()
                INSTANCE = instance
                return instance
            }
        }
    }
}