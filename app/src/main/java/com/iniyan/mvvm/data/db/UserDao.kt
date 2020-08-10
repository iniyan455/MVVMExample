package net.simplifiedcoding.mvvmsampleapp.data.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import net.simplifiedcoding.mvvmsampleapp.data.db.entities.CURRENT_USER_ID
import net.simplifiedcoding.mvvmsampleapp.data.db.entities.User


/**
 * Data access object
 */
@Dao
interface UserDao{

    /**
     * User Insert and get Long return
     * Insert command onConflict same id or same key - use  onConflict = OnConflictStrategy.REPLACE
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsert(user: User) : Long

    /**
     * To retrieve data from database uid equals to current userid alredy defined in entity class and returns us live data type user
     */
    @Query("SELECT * FROM user WHERE uid = $CURRENT_USER_ID")
    fun getuser() : LiveData<User>

}