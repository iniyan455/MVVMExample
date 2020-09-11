package com.iniyan.mvvm.data.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.iniyan.mvvm.data.db.entities.CURRENT_USER_ID
import com.iniyan.mvvm.data.db.entities.User


/**
 * Data access object
 */
@Dao
interface UserDao{

    /**
     * User Insert and get Long return
     * Insert command onConflict same id or same key - use  onConflict = OnConflictStrategy.REPLACE
     * need to make function is suspend else crash
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsert(user: User) : Long

    /**
     * To retrieve data from database uid equals to current userid alredy defined in entity class and returns us live data type user
     */
    @Query("SELECT * FROM user WHERE uid = $CURRENT_USER_ID")
    fun getuser() : LiveData<User>

}