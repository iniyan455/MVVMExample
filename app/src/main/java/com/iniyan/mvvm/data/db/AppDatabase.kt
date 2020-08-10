package net.simplifiedcoding.mvvmsampleapp.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import net.simplifiedcoding.mvvmsampleapp.data.db.entities.Quote
import net.simplifiedcoding.mvvmsampleapp.data.db.entities.User


/**
 * Add all entity class in entities and versions
 */
@Database(
    entities = [User::class, Quote::class],
    version = 1
)
/**
 * Extends RoomDatabase and method should be abstract and annoate as @Database
 */
abstract class AppDatabase : RoomDatabase() {

    abstract fun getUserDao(): UserDao
    abstract fun getQuoteDao(): QuoteDao

    /**
     * Create companion object for room and voliate and
     */
    companion object {

        // visible for all the thread
        @Volatile
        private var instance: AppDatabase? = null
        // used for  make sure we do not create two instance for our database
        private val LOCK = Any()

        // when we create appdatabase we need to pass context to invoke method check if instance is not null it will returns the instance help of the operator
        // sychronized again check instance is null or not if null create object
        operator fun invoke(context: Context) = instance ?: synchronized(LOCK) {
            instance ?: buildDatabase(context).also {
                instance = it
            }
        }


        // create database having three paramaters always pass applicationContext , classname which is extended, databasename.db
        private fun buildDatabase(context: Context) =
            Room.databaseBuilder(
                context.applicationContext,
                AppDatabase::class.java,
                "MyDatabase.db"
            ).build()
    }
}