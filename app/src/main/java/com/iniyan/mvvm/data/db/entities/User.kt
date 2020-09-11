package com.iniyan.mvvm.data.db.entities

import androidx.room.Entity
import androidx.room.PrimaryKey


const val CURRENT_USER_ID = 0

/** Entity Representation */
@Entity(tableName = "User")
data class User(
    var id: Int? = null,
    var name: String? = null,
    var email: String? = null,
    var password: String? = null,
    var email_verified_at: String? = null,
    var created_at: String? = null,
    var updated_at: String? = null
){

    /** autogenerate is disabled because only authenticate user data only will store and if any
     * conflicts it will replace the data with same id  **/

    @PrimaryKey(autoGenerate = false)
    var uid: Int = CURRENT_USER_ID
}