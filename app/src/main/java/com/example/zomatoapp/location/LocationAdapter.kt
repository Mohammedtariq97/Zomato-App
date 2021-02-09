package com.example.zomatoapp.location

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.zomatoapp.R
import com.example.zomatoapp.model.LocationModel

class LocationAdapter(private val list:List<LocationModel>):RecyclerView.Adapter<LocationAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater
            .from(parent.context).inflate(R.layout.location_layout,parent,false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val data = list[position]
        holder.locationTitle.text = data.title
        holder.location.text = data.city_name
        holder.itemView.setOnClickListener(object:View.OnClickListener{
            override fun onClick(v: View) {
                v.findNavController().navigate(R.id.action_locationFragment_to_restaurantFragment)
            }

        })
    }

    override fun getItemCount(): Int {
        return list.size
    }

    class ViewHolder(itemView: View):RecyclerView.ViewHolder(itemView) {
        val locationTitle = itemView.findViewById<TextView>(R.id.locationTitle)
        val location = itemView.findViewById<TextView>(R.id.location)
    }


}