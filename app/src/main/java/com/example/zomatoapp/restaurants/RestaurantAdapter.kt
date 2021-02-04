package com.example.zomatoapp.restaurants

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.zomatoapp.R
import com.example.zomatoapp.model.RestaurantModel
import com.example.zomatoapp.search.SearchFragmentDirections

class RestaurantAdapter(private val context: Context, private val list: List<RestaurantModel>) :
    RecyclerView.Adapter<RestaurantAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.restaurant_list_layout, parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val data = list[position]
        Glide.with(context)
            .load(data.restaurant.featured_image)
            .placeholder(R.drawable.placeholder)
            .apply(RequestOptions().fitCenter())
            .into(holder.restaurantImageView)
        holder.restaurantTitle.text = data.restaurant.name
        holder.restaurantRating.text = data.restaurant.user_rating.aggregate_rating
        holder.restaurantLocation.text = data.restaurant.location.locality
        val resImage = data.restaurant.featured_image
        val resName = data.restaurant.name
        val resCuisines = data.restaurant.cuisines
        val locality = data.restaurant.location.locality_verbose
        val rating = data.restaurant.user_rating.aggregate_rating
        val avgCost = data.restaurant.average_cost_for_two
        val address = data.restaurant.location.address
        val timings = data.restaurant.timings
        holder.itemView.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                v?.findNavController()?.navigate(
                    SearchFragmentDirections
                        .actionSearchFragmentToRestaurantDetailFragment(
                            resImage, resName,
                            resCuisines, locality, rating, avgCost, address,timings
                        )
                )
            }

        })
    }

    override fun getItemCount(): Int {
        return list.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val restaurantImageView = itemView.findViewById<ImageView>(R.id.restaurantImageView)
        val restaurantTitle = itemView.findViewById<TextView>(R.id.restaurantTitle)
        val restaurantRating = itemView.findViewById<TextView>(R.id.restaurantRating)
        val restaurantLocation = itemView.findViewById<TextView>(R.id.restaurantLocation)
    }
}