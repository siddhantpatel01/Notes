package com.example.notes.ROOM_DB

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase

@Database(entities = [Notes::class], version = 2, exportSchema = false)
abstract class myDatabase : RoomDatabase() {
    abstract fun getNoteDao() :NoteDao

    companion object {

        // Singleton prevents multiple instances of database opening at the
        // same time.

        val migration_1_2 = object : Migration(1, 2) {
            override fun migrate(database: SupportSQLiteDatabase) {
                database.execSQL("ALTER TABLE Notes ADD COLUMN Title TEXT NOT NULL DEFAULT ''")
            }
        }

        @Volatile
        private var INSTANCE: myDatabase? = null

        fun getDatabase(context: Context): myDatabase {
            // if the INSTANCE is not null, then return it,
            // if it is, then create the database
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    myDatabase::class.java,
                    "Siddhant"
                ).addMigrations(migration_1_2).build()
                INSTANCE = instance
                // return instance
                instance
            }
        }
    }
}
