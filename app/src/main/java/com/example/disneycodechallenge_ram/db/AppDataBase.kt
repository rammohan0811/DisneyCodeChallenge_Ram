package com.example.disneycodechallenge_ram.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(entities = [ReservedGuest::class, NonReservedGuest::class],
    version = 1, exportSchema = false)
@TypeConverters(Converters::class)
abstract class AppDataBase : RoomDatabase() {

    abstract fun GuestDao() : GuestDao

    companion object {
        private var INSTANCE: AppDataBase? = null

        fun getInstance(context: Context): AppDataBase? {
            if (INSTANCE ==  null) {
                synchronized(AppDataBase::class) {
                    INSTANCE = Room.databaseBuilder(context.applicationContext,
                        AppDataBase::class.java,
                    "GuestData.db").allowMainThreadQueries()
                        .build()
                }
            }
            return INSTANCE
        }

        fun destroyInstance() {
            INSTANCE = null
        }
    }
}