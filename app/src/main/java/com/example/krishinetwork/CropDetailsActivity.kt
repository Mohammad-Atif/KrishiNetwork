package com.example.krishinetwork

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.krishinetwork.adapters.CropRecyclerViewAdapter
import com.example.krishinetwork.databinding.ActivityCropDetailsBinding
import com.example.krishinetwork.db.KrishiDatabase
import com.example.krishinetwork.models.OtherMandi
import com.example.krishinetwork.repository.KrishiRepository
import com.example.krishinetwork.utils.TopSpacingDecoration
import com.example.krishinetwork.viewmodels.KrishiViewModel
import com.example.krishinetwork.viewmodels.KrishiViewModelProvider

class CropDetailsActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCropDetailsBinding
    lateinit var cropRecyclerViewAdapter: CropRecyclerViewAdapter
    lateinit var viewModel: KrishiViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityCropDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initRecyclerView()
        val krishiRepository= KrishiRepository(KrishiDatabase.getInstance(application))

        val krishiViewModelProvider= KrishiViewModelProvider(krishiRepository)

        viewModel= ViewModelProvider(this,krishiViewModelProvider).get(KrishiViewModel::class.java)
        viewModel.getMandiData()
        viewModel.mandiList.observe(this, Observer {
            cropRecyclerViewAdapter.submitList(it as MutableList<OtherMandi>)
        })

    }


    private fun initRecyclerView()
    {
        binding.cropsrecyclerview.apply {
            layoutManager= LinearLayoutManager(applicationContext)
            cropRecyclerViewAdapter= CropRecyclerViewAdapter()
            val topspace= TopSpacingDecoration(10)
            addItemDecoration(topspace)
            adapter= cropRecyclerViewAdapter
        }
    }

}