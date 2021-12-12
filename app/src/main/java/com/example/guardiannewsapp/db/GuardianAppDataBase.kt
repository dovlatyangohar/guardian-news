package com.example.guardiannewsapp.db

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.Database
import com.example.guardiannewsapp.network.models.Result


@Database(entities = [Result::class], version = 1, exportSchema = false)
abstract class GuardianAppDataBase : RoomDatabase() {
    abstract fun getGuardianDao(): GuardianAppDao

    companion object {
        @Volatile
        private var INSTANCE: GuardianAppDataBase? = null

        fun getDatabaseInstance(context: Context): GuardianAppDataBase {
            if (INSTANCE == null) {
                synchronized(GuardianAppDataBase::class.java) {
                    if (INSTANCE == null) {
                        INSTANCE = Room.databaseBuilder(
                            context.applicationContext,
                            GuardianAppDataBase::class.java, "news_database"
                        ).build()
                    }
                }
            }
            return INSTANCE!!
        }
    }
}