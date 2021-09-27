package com.example.krishinetwork.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.krishinetwork.R
import com.example.krishinetwork.models.OtherMandi

class CropRecyclerViewAdapter: RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var listOfMandi: MutableList<OtherMandi> = mutableListOf<OtherMandi>()


    inner class CropViewHolder(view: View):RecyclerView.ViewHolder(view){
        val cropName: TextView = view.findViewById(R.id.txtCropName)
        val mandiDistance: TextView = view.findViewById(R.id.txtDistance)
        val lastDate : TextView = view.findViewById(R.id.txtLastDate)
        val cropLocation : TextView = view.findViewById(R.id.txtCropLocation)
        val cropImage : ImageView = view.findViewById(R.id.CropImg)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return CropViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.mandi_card_view,parent,false)
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when(holder)
        {
            is CropViewHolder->{
                holder.cropName.text= listOfMandi[position].hindi_name
                val distanceInKm=listOfMandi[position].km.toString()+" km"
                holder.mandiDistance.text= distanceInKm
                holder.lastDate.text= listOfMandi[position].last_date.toString()
                val locationOfMandi= listOfMandi[position].market.toString()+","+listOfMandi[position].district.toString()+","+listOfMandi[position].state.toString()
                holder.cropLocation.text= locationOfMandi
                Glide.with(holder.itemView).load(listOfMandi[position].image).into(holder.cropImage)
            }
        }
    }

    override fun getItemCount(): Int {
        return listOfMandi.size
    }

    fun submitList(list: MutableList<OtherMandi>){
        listOfMandi=list
        notifyDataSetChanged()
    }
}