package com.example.krishinetwork.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.krishinetwork.models.KrishiUser

@Dao
interface KrishiDao {
    @Insert
    suspend fun insertUserDetails(krishiUser: KrishiUser)

    @Query("select * from KrishiUser")
    suspend fun getUserDetails() : List<KrishiUser>

}