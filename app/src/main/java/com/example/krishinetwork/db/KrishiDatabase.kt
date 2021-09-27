package com.example.krishinetwork.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.krishinetwork.daos.KrishiDao
import com.example.krishinetwork.models.KrishiUser


@Database(entities = [KrishiUser::class],
version = 1)
abstract class KrishiDatabase : RoomDatabase() {
    abstract fun getKrishiDao (): KrishiDao

    companion object{
        fun getInstance(context: Context): KrishiDatabase{
            return Room.databaseBuilder(context,KrishiDatabase::class.java,"KrishiDatabase").build()
        }
    }
}