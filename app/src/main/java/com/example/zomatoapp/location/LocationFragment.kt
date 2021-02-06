package com.example.zomatoapp.location

import android.Manifest
import android.content.pm.PackageManager
import android.location.Geocoder
import android.location.Location
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import androidx.transition.TransitionInflater
import com.example.zomatoapp.R
import com.example.zomatoapp.restaurants.RestaurantFragment
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.gms.tasks.Task
import java.io.IOException
import java.util.*

class LocationFragment : Fragment() {
    companion object {
        const val TAG = "LocationFragment"
        const val REQUEST_LOCATION_PERMISSION = 2
    }
    lateinit var locationSearchEditText:EditText
    lateinit var recyclerViewLocation:RecyclerView
    lateinit var currentLocation:TextView
    private lateinit var client: FusedLocationProviderClient

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        client = LocationServices.getFusedLocationProviderClient(this.requireContext())
        return inflater.inflate(R.layout.fragment_location, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        locationSearchEditText = view.findViewById(R.id.locationSearchEditText)
        recyclerViewLocation = view.findViewById(R.id.recyclerViewLocation)
        currentLocation = view.findViewById(R.id.currentLocation)

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val inflater = TransitionInflater.from(requireContext())
        enterTransition = inflater.inflateTransition(R.transition.fade)
        currentLocation.setOnClickListener(object:View.OnClickListener{
            override fun onClick(v: View?) {
                getCurrentLocation()
            }

        })
        locationSearchEditText.addTextChangedListener(object:TextWatcher{
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

            }

            override fun afterTextChanged(s: Editable?) {

            }

        })
    }

    private fun getCurrentLocation() {
        if (ContextCompat.checkSelfPermission(
                this.requireContext(),
                Manifest.permission.ACCESS_FINE_LOCATION
            )
            == PackageManager.PERMISSION_GRANTED
        ) {
            client.lastLocation.addOnCompleteListener(object : OnCompleteListener<Location> {
                override fun onComplete(task: Task<Location>) {
                    val location = task.getResult()
                    if (location != null) {
                        try {
                            val geocoder = Geocoder(context, Locale.getDefault())
                            val address = geocoder.getFromLocation(
                                location.latitude, location.longitude, 1
                            )
                            Log.d(RestaurantFragment.TAG, "${address.get(0).latitude}")
                            Log.d(RestaurantFragment.TAG, "${address.get(0).longitude}")
                            Log.d(RestaurantFragment.TAG, address[0].adminArea)
                            Log.d(RestaurantFragment.TAG, address[0].featureName)
                            Log.d(RestaurantFragment.TAG, address[0].getAddressLine(0))
                            Log.d(RestaurantFragment.TAG, address[0].locality)
                            Log.d(RestaurantFragment.TAG, address[0].subAdminArea)
//                            Log.d(RestaurantFragment.TAG, address[0].subLocality.toString())
                            Log.d(RestaurantFragment.TAG, address[0].postalCode)
                            Log.d(RestaurantFragment.TAG, address[0].countryName)
                            val addressString = address[0].getAddressLine(0)
                            findNavController().navigate(R.id.action_locationFragment_to_restaurantFragment)
                        } catch (e: IOException) {
                            e.printStackTrace()
                        }
                    }
                }

            })
        } else {
            requestPermissions(
                arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
                RestaurantFragment.REQUEST_LOCATION_PERMISSION
            )
        }
    }

}