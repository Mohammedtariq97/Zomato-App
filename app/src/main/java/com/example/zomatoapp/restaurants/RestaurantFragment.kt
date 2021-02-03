package com.example.zomatoapp.restaurants

import android.Manifest
import android.content.pm.PackageManager
import android.location.Geocoder
import android.location.Location
import android.location.LocationProvider
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ProgressBar
import androidx.appcompat.widget.SearchView
import androidx.core.app.ActivityCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.transition.TransitionInflater
import com.example.zomatoapp.R
import com.example.zomatoapp.model.NearByRestaurant
import com.example.zomatoapp.model.RestaurantModel
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationServices
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.gms.tasks.Task
import com.google.android.material.snackbar.Snackbar
import okio.IOException
import java.util.*


class RestaurantFragment : Fragment() {
    companion object {
        const val TAG = "RestaurantFragment"
        const val REQUEST_LOCATION_PERMISSION = 1
    }

    lateinit var restaurantRecyclerViewList: RecyclerView
    lateinit var searchLocationSearchEditText: SearchView
    lateinit var restaurantFragmentViewModel: RestaurantFragmentViewModel
    lateinit var searchRestaurantEditText: EditText
    private lateinit var client: FusedLocationProviderClient
    lateinit var nearByResButton: Button
    lateinit var indeterminateBar: ProgressBar

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        restaurantFragmentViewModel = ViewModelProvider(this)
            .get(RestaurantFragmentViewModel::class.java)
        client = LocationServices.getFusedLocationProviderClient(this.requireContext())
        return inflater.inflate(R.layout.fragment_restaurant, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        restaurantRecyclerViewList = view.findViewById(R.id.restaurantRecyclerViewList)
        searchLocationSearchEditText = view.findViewById(R.id.searchLocationSearchEditText)
        searchRestaurantEditText = view.findViewById(R.id.searchRestaurantEditText)
        nearByResButton = view.findViewById(R.id.nearByResButton)
        indeterminateBar = view.findViewById(R.id.indeterminateBar)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        requestLocationPermission()
        val inflater = TransitionInflater.from(requireContext())
        exitTransition = inflater.inflateTransition(R.transition.fade)
        searchLocationSearchEditText.setOnQueryTextListener(object :
            SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                if (query != null) {
                    restaurantFragmentViewModel.callSearchLocationApi(query)
                    restaurantFragmentViewModel.liveDataLocationSearch.observe(viewLifecycleOwner,
                        Observer {
                            if (searchRestaurantEditText.text.toString() != "") {
                                restaurantFragmentViewModel.callSearchRestaurantApi(
                                    it.location_suggestions[0].entity_id.toString(),
                                    searchRestaurantEditText.text.toString()
                                )
                                restaurantFragmentViewModel.livedataRestaurantSearch.observe(
                                    viewLifecycleOwner,
                                    Observer {
                                        setRecyclerView(it.restaurants)
                                    })
                            } else {
                                view?.let { it1 ->
                                    Snackbar.make(
                                        it1,
                                        "Please enter Restaurant name",
                                        Snackbar.LENGTH_SHORT
                                    ).show()
                                }
                            }
                        })
                }
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }

        })
    }


    private fun requestLocationPermission() {
        if (ActivityCompat.checkSelfPermission(
                this.requireContext(),
                Manifest.permission.ACCESS_FINE_LOCATION
            )
            == PackageManager.PERMISSION_GRANTED
        ) {
            nearByResButton.visibility = View.GONE
            client.lastLocation.addOnCompleteListener(object : OnCompleteListener<Location> {
                override fun onComplete(task: Task<Location>) {
                    val location = task.getResult()
                    if (location != null) {
                        try {
                            val geocoder = Geocoder(context, Locale.getDefault())
                            val address = geocoder.getFromLocation(
                                location.latitude, location.longitude, 1
                            )
                            Log.d(TAG, "${address.get(0).latitude}")
                            Log.d(TAG, "${address.get(0).longitude}")
                            restaurantFragmentViewModel
                                .callNearByRestaurantApi(
                                    address[0].latitude.toString(),
                                    address[0].longitude.toString()
                                )
                            restaurantFragmentViewModel.liveDataNearByResSearch.observe(
                                viewLifecycleOwner,
                                Observer {
                                    indeterminateBar.visibility = View.GONE
                                    setRecyclerView1(it.nearby_restaurants)
                                })
                        } catch (e: IOException) {
                            e.printStackTrace()
                        }
                    }
                }

            })
        } else {
            ActivityCompat.requestPermissions(
                this.requireActivity(),
                arrayOf(Manifest.permission.ACCESS_FINE_LOCATION), 44
            )

        }
    }

    private fun setRecyclerView1(list: List<NearByRestaurant>) {
        val mAdapter = RestaurantAdapter1(this.requireContext(), list)
        val mLayoutManager = LinearLayoutManager(this.requireContext())
        restaurantRecyclerViewList.layoutManager = mLayoutManager
        mLayoutManager.orientation = RecyclerView.VERTICAL
        restaurantRecyclerViewList.itemAnimator = DefaultItemAnimator()
        restaurantRecyclerViewList.adapter = mAdapter
    }

    private fun setRecyclerView(list: List<RestaurantModel>) {
        val mAdapter = RestaurantAdapter(this.requireContext(), list)
        val mLayoutManager = LinearLayoutManager(this.requireContext())
        restaurantRecyclerViewList.layoutManager = mLayoutManager
        mLayoutManager.orientation = RecyclerView.VERTICAL
        restaurantRecyclerViewList.itemAnimator = DefaultItemAnimator()
        restaurantRecyclerViewList.adapter = mAdapter
    }

}