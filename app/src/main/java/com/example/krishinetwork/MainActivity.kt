package com.example.krishinetwork

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.view.View
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.krishinetwork.databinding.ActivityMainBinding
import com.example.krishinetwork.viewmodels.KrishiViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val reqCode=200
    lateinit var viewModel: KrishiViewModel
    var imgBitmap: Bitmap? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        hideViews()
        viewModel=ViewModelProvider(this).get(KrishiViewModel::class.java)

        binding.btnCameraOpen.setOnClickListener {
            val cameraIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            startActivityForResult(cameraIntent, reqCode)
        }

        binding.btnSubmit.setOnClickListener {
            checkEntries()
        }

        viewModel.userName.observe(this, Observer { name->
            if(name!=null)
            {
                binding.txtNameSubmitted.visibility=View.VISIBLE
                binding.txtNameShow.visibility=View.VISIBLE
                binding.txtNameShow.text=name

            }
        })

        viewModel.userEmail.observe(this, Observer {email->
            if(email!=null)
            {
                binding.txtEmailSubmitted.visibility=View.VISIBLE
                binding.txtEmailShow.visibility=View.VISIBLE
                binding.txtEmailShow.text=email
            }
        })
        viewModel.userImage.observe(this, Observer {
            if(it!=null)
            {
                binding.imgUser.visibility=View.VISIBLE
                binding.imgUser.setImageBitmap(it)
            }
        })

    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK && requestCode == reqCode && data != null){
            imgBitmap = data.extras?.get("data") as Bitmap
//            viewModel.submitEntries(binding.txtInputName.text.toString(),
//                binding.txtInputEmail.text.toString(),
//                imgBitmap!!
//            )

        }
    }

    /*
    Function to check weather user entered the correct details or not
     */
    private fun checkEntries()
    {
        if(binding.txtInputName.text.isNullOrBlank())
        {
            Toast.makeText(this,"Enter Name",Toast.LENGTH_SHORT).show()
            binding.txtInputName.requestFocus()
            return
        }
        if(viewModel.isValidEmail(binding.txtInputEmail.text.toString()))
        {
            Toast.makeText(this,"Enter Valid Email",Toast.LENGTH_SHORT).show()
            binding.txtInputEmail.requestFocus()
            return
        }
        if(imgBitmap==null)
        {
            Toast.makeText(this,"Select Image",Toast.LENGTH_SHORT).show()
            return
        }

        viewModel.submitEntries(binding.txtInputName.text.toString(),
        binding.txtInputEmail.text.toString(),
        imgBitmap!!)


    }

    private fun hideViews()
    {
        binding.txtEmailSubmitted.visibility= View.INVISIBLE
        binding.txtEmailShow.visibility=View.INVISIBLE
        binding.txtNameShow.visibility=View.INVISIBLE
        binding.txtNameSubmitted.visibility=View.INVISIBLE
        binding.imgUser.visibility=View.INVISIBLE

    }
}