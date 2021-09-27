package com.example.krishinetwork.repository

import com.example.krishinetwork.api.RetrofitInstance
import com.example.krishinetwork.db.KrishiDatabase
import com.example.krishinetwork.models.KrishiUser

class KrishiRepository(private val db:KrishiDatabase) {

    suspend fun addUser(krishiUser: KrishiUser) = db.getKrishiDao().insertUserDetails(krishiUser)

    suspend fun getUser() = db.getKrishiDao().getUserDetails()

    suspend fun getCropDetails() = RetrofitInstance.api.getCopsDetails(28.44108136,77.0526054,89,"hi",10)


}