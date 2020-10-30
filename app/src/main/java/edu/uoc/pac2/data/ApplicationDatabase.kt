package edu.uoc.pac2.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

/**
 * Room Application Database
 */


@Database(
        entities = [Book::class],
        version = 1
)

abstract class ApplicationDatabase: RoomDatabase() {
    abstract fun bookDao(): BookDao

    companion object {

        @Volatile private var INSTANCE: ApplicationDatabase? = null

        fun getInstance(context: Context): ApplicationDatabase = INSTANCE ?: synchronized(this) {
                    INSTANCE ?: buildDatabase(context).also { INSTANCE = it }
        }

        private fun buildDatabase(context: Context) =
                Room.databaseBuilder(context.applicationContext,
                        ApplicationDatabase::class.java, "book_bd.db").fallbackToDestructiveMigration()
                        .build()
    }


//        companion object {
//        @Volatile private var instance: ApplicationDatabase? = null
//        private val LOCK = Any()
//
//        operator fun getInstance(context: Context)= instance ?: synchronized(LOCK){
//            instance ?: buildDatabase(context).also { instance = it}
//        }
//
//        private fun buildDatabase(context: Context) = Room.databaseBuilder(context,
//                ApplicationDatabase::class.java, "book.db")
//                .build()
//    }



//    companion object {
//        private var INSTANCE: ApplicationDatabase? = null
//        const val DB_VERSION = 1
//
//        fun getInstance(context: Context): ApplicationDatabase? {
//            if (INSTANCE == null) {
//                synchronized(ApplicationDatabase::class) {
//                    INSTANCE = Room.databaseBuilder(
//                            context.applicationContext,
//                            ApplicationDatabase::class.java,
//                            "databaseTests"
//                    ).build()
//                }
//            }
//            return INSTANCE
//        }
//
//        fun destroyInstance() {
//            INSTANCE = null
//        }
//    }





}
