package com.example.zomatoapp.location

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.zomatoapp.R
import com.example.zomatoapp.model.LocationModel
import com.example.zomatoapp.restaurants.RestaurantFragment.Companion.TAG

class LocationAdapter(private val list:List<LocationModel>):RecyclerView.Adapter<LocationAdapter.ViewHolder>() {

    lateinit var data :LocationModel
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater
            .from(parent.context).inflate(R.layout.location_layout,parent,false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        data = list[position]
        holder.locationTitle.text = data.title
        holder.location.text = data.city_name
        val location = data.title
        val addressString = data.city_name

        holder.itemView.setOnClickListener(object:View.OnClickListener{
            override fun onClick(v: View) {
                v.findNavController().previousBackStackEntry?.savedStateHandle?.set("key", data.title)
                v.findNavController().navigate(R.id.action_locationsFragment_to_restaurantFragment)

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