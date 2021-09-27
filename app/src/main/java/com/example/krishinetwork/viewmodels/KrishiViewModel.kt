package com.example.krishinetwork.viewmodels

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.provider.MediaStore
import android.util.Base64
import android.widget.Toast
import androidx.core.app.ActivityCompat.startActivityForResult
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.krishinetwork.models.KrishiUser
import com.example.krishinetwork.repository.KrishiRepository
import kotlinx.coroutines.launch
import java.io.ByteArrayOutputStream

class KrishiViewModel(
    val krishiRepository: KrishiRepository
) : ViewModel() {
    val userName:MutableLiveData<String> = MutableLiveData()
    val userEmail:MutableLiveData<String> = MutableLiveData()
    val userImage: MutableLiveData<Bitmap> = MutableLiveData()
    private val REQUEST_CODE = 200



    init {
        getUserDetails()
    }
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



        viewModelScope.launch {
            krishiRepository.addUser(KrishiUser(
                name,
                email,
                imagebitmapString
            ))
        }

    }

    fun getUserDetails()=viewModelScope.launch {
        val listOfUsers = krishiRepository.getUser()
        if(listOfUsers.isNotEmpty())
        {
            val krishiUser= listOfUsers[listOfUsers.size-1]
            val imgBit=stringToBitmap(krishiUser.userImageBitmapString!!)
            userName.postValue(krishiUser.userName!!)
            userEmail.postValue(krishiUser.userEmail!!)
            userImage.postValue(imgBit!!)
        }
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

    private fun stringToBitmap(string: String): Bitmap? {
        val imageBytes = Base64.decode(string, Base64.DEFAULT)
        val decodedImage = BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.size)
        return decodedImage
    }

}