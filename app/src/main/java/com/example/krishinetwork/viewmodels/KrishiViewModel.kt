package com.example.krishinetwork.viewmodels

import android.content.Intent
import android.net.Uri
import android.provider.MediaStore
import androidx.core.app.ActivityCompat.startActivityForResult
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class KrishiViewModel : ViewModel() {
    val userName:MutableLiveData<String> = MutableLiveData()
    val userEmail:MutableLiveData<String> = MutableLiveData()
    private val REQUEST_CODE = 200


    /*
    Function to check weather the entries are correct and to submit the information
     */
//    fun submit(name:String,email:String)
//    {
//        val cameraIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
//        startActivityForResult(cameraIntent, REQUEST_CODE)
//    }
}