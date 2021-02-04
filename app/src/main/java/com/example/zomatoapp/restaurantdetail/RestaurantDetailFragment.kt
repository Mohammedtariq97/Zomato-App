package com.example.zomatoapp.restaurantdetail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.transition.TransitionInflater
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.zomatoapp.R


class RestaurantDetailFragment : Fragment() {
    lateinit var args:RestaurantDetailFragmentArgs
    lateinit var restaurantImage:ImageView
    lateinit var restaurantName:TextView
    lateinit var cuisines:TextView
    lateinit var localityVerbose:TextView
    lateinit var rating:TextView
    lateinit var avgCost:TextView
    lateinit var address:TextView
    lateinit var timings:TextView


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        args = RestaurantDetailFragmentArgs.fromBundle(requireArguments())
        return inflater.inflate(R.layout.fragment_restaurant_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        restaurantImage = view.findViewById(R.id.restaurantImage)
        restaurantName = view.findViewById(R.id.restaurantName)
        cuisines = view.findViewById(R.id.cuisines)
        localityVerbose =view.findViewById(R.id.localityVerbose)
        rating = view.findViewById(R.id.rating)
        avgCost = view.findViewById(R.id.avgCost)
        address = view.findViewById(R.id.address)
        timings = view.findViewById(R.id.timings)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val inflater = TransitionInflater.from(requireContext())
        enterTransition = inflater.inflateTransition(R.transition.slide_right)
        Glide.with(requireContext())
            .load(args.resImage)
            .placeholder(R.drawable.placeholder)
            .apply(RequestOptions().fitCenter())
            .into(restaurantImage)
        restaurantName.text = args.resName
        cuisines.text = formatCuisines(args.resCuisines)
        localityVerbose.text = args.locality
        rating.text = formatRating(args.rating)
        avgCost.text = formatCost(args.avgCost.toString())
        address.text =args.address
        timings.text = formatTimings(args.timings)
    }

    private fun formatCost(cost: String): String {
        return "Cost for two - ₹$cost(approx)"
    }

    private fun formatTimings(timings: String): String {
        if(timings != ""){
            return "Timings - $timings"
        }else{
            return "Timings - 11am to 11pm"
        }

    }

    private fun formatRating(rating: String): String {
        return "Rating - $rating"
    }

    private fun formatCuisines(resCuisines: String): String {
        return "Quick Bites - $resCuisines"
    }


}