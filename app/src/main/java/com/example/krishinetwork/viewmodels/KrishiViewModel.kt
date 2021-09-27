package com.example.krishinetwork.viewmodels

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.provider.MediaStore
import android.util.Base64
import android.widget.Toast
import androidx.core.app.ActivityCompat.startActivityForResult
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import java.io.ByteArrayOutputStream

class KrishiViewModel : ViewModel() {
    val userName:MutableLiveData<String> = MutableLiveData()
    val userEmail:MutableLiveData<String> = MutableLiveData()
    val userImage: MutableLiveData<Bitmap> = MutableLiveData()
    private val REQUEST_CODE = 200



    /*
    Function to submit entries in the database and then update the Livedata from which the ui elements
    of the main activity get updated
     */
    fun submitEntries(name:String,email:String,imageBitmap: Bitmap)
    {
        //converting bitmap to string for easily storing it in room
        val imagebitmapString = bitMapToString(imageBitmap)
        userName.postValue(name)
        userEmail.postValue(email)
        userImage.postValue(imageBitmap)

    }


    fun isValidEmail(email: String): Boolean {
        return !(email.isNotEmpty() && android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches())
    }

    fun bitMapToString(bitmap: Bitmap): String {
        val baos = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, baos)
        val b = baos.toByteArray()
        return Base64.encodeToString(b, Base64.DEFAULT)
    }
}