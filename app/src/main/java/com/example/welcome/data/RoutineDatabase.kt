package com.example.welcome.data



import android.content.Context
import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.Room

@Database(
    entities = [Routine::class], //entities are the tables in the database
    version = 1, //version of the database

)
abstract class RoutineDatabase: RoomDatabase() {
    abstract val dao: RoutineDAO

    companion object {
        @Volatile
        private var INSTANCE: RoutineDatabase? = null

        fun getDatabase(context: Context): RoutineDatabase { //getDatabase is a function that returns the database instance
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    RoutineDatabase::class.java,
                    "routine_database" //name of the database
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}

