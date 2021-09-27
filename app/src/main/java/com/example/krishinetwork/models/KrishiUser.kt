package com.example.krishinetwork.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class KrishiUser(
    val userName:String?,
    val userEmail:String?,
    val userImageBitmapString: String?,
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
)
