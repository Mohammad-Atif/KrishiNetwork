package com.example.krishinetwork.repository

import com.example.krishinetwork.db.KrishiDatabase
import com.example.krishinetwork.models.KrishiUser

class KrishiRepository(private val db:KrishiDatabase) {

    suspend fun addUser(krishiUser: KrishiUser) = db.getKrishiDao().insertUserDetails(krishiUser)

    suspend fun getUser() = db.getKrishiDao().getUserDetails()

}