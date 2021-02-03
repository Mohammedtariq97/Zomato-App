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
import com.example.zomatoapp.model.NearByRestaurant
import org.w3c.dom.Text

class RestaurantAdapter1(private val context: Context, private val list: List<NearByRestaurant>) :
    RecyclerView.Adapter<RestaurantAdapter1.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.restaurant_list_layout_nearby, parent, false
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
        holder.restaurantRating.text = formatRating(data.restaurant.user_rating.aggregate_rating)
        holder.cuisines.text = data.restaurant.cuisines
        holder.avgCost.text = formatCost(data.restaurant.average_cost_for_two)
        val resImage = data.restaurant.featured_image
        val resName = data.restaurant.name
        val resCuisines = data.restaurant.cuisines
        val locality = data.restaurant.location.locality_verbose
        val rating = data.restaurant.user_rating.aggregate_rating
        val avgCost = data.restaurant.average_cost_for_two
        val address = data.restaurant.location.address
        holder.itemView.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                v?.findNavController()?.navigate(
                    RestaurantFragmentDirections
                        .actionRestaurantFragmentToRestaurantDetailFragment(
                            resImage, resName,
                            resCuisines, locality, rating, avgCost, address
                        )
                )
            }
        })
    }

    override fun getItemCount(): Int {
        return list.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val restaurantImageView = itemView.findViewById<ImageView>(R.id.resImage)
        val restaurantTitle = itemView.findViewById<TextView>(R.id.resName)
        val restaurantRating = itemView.findViewById<TextView>(R.id.rating)
        val cuisines = itemView.findViewById<TextView>(R.id.cuisines)
        val avgCost = itemView.findViewById<TextView>(R.id.avgCost)
    }

    private fun formatCost(cost: Long): String {
        val costForOne = (cost/2).toString()
        return "₹$costForOne for one"
    }

    private fun formatRating(rating: String): String {
        return "$rating/5"
    }
}