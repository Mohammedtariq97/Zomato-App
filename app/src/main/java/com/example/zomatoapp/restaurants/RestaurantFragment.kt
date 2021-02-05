package com.example.zomatoapp.restaurants

import android.Manifest
import android.content.pm.PackageManager
import android.location.Geocoder
import android.location.Location
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.TextView
import androidx.appcompat.widget.SearchView
import androidx.core.content.ContextCompat.checkSelfPermission
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.transition.TransitionInflater
import com.example.zomatoapp.R
import com.example.zomatoapp.model.RestaurantModel
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.gms.tasks.Task
import okio.IOException
import java.util.*


@Suppress("DEPRECATION")
class RestaurantFragment : Fragment() {
    companion object {
        const val TAG = "RestaurantFragment"
        const val REQUEST_LOCATION_PERMISSION = 1
    }

    lateinit var restaurantRecyclerViewList: RecyclerView
    lateinit var searchLocationTextView: TextView
    lateinit var restaurantFragmentViewModel: RestaurantFragmentViewModel
    lateinit var searchRestaurantTextView: TextView
    private lateinit var client: FusedLocationProviderClient
    lateinit var indeterminateBar: ProgressBar
    var start = 0
    var isLoading = false
    lateinit var mAdapter:RestaurantAdapter1
    lateinit var geocoder:Geocoder
    lateinit var lat:String
    lateinit var lon:String


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
        searchLocationTextView = view.findViewById(R.id.searchLocationTextView)
        searchRestaurantTextView = view.findViewById(R.id.searchRestaurantTextView)
        indeterminateBar = view.findViewById(R.id.indeterminateBar)

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        requestLocationPermission()
        val inflater = TransitionInflater.from(requireContext())
        exitTransition = inflater.inflateTransition(R.transition.fade)
        searchRestaurantTextView.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                v?.findNavController()?.navigate(R.id.action_restaurantFragment_to_searchFragment)
            }
        })

    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when (requestCode) {
            REQUEST_LOCATION_PERMISSION -> {

                if ((grantResults.isNotEmpty() &&
                            grantResults[0] == PackageManager.PERMISSION_GRANTED)
                ) {
                    if (checkSelfPermission(
                            this.requireContext(),
                            Manifest.permission.ACCESS_FINE_LOCATION
                        ) != PackageManager.PERMISSION_GRANTED && checkSelfPermission(
                            this.requireContext(),
                            Manifest.permission.ACCESS_COARSE_LOCATION
                        ) != PackageManager.PERMISSION_GRANTED
                    ) {

                        return
                    }
                    client.lastLocation.addOnCompleteListener(object :
                        OnCompleteListener<Location> {
                        override fun onComplete(task: Task<Location>) {
                            val location = task.getResult()
                            if (location != null) {
                                try {
                                    geocoder = Geocoder(context, Locale.getDefault())
                                    val address = geocoder.getFromLocation(
                                        location.latitude, location.longitude, 1
                                    )
                                    Log.d(TAG, "${address.get(0).latitude}")
                                    Log.d(TAG, "${address.get(0).longitude}")
                                    lat = address.get(0).latitude.toString()
                                    lon = address.get(0).longitude.toString()
                                    restaurantFragmentViewModel
                                        .callNearByRestaurantApi(
                                            address[0].latitude.toString(),
                                            address[0].longitude.toString()
                                        )
                                    restaurantFragmentViewModel.liveDataNearByResSearch.observe(
                                        viewLifecycleOwner,
                                        Observer {
                                            Log.d(TAG, "it = $it")
                                            indeterminateBar.visibility = View.GONE
                                            restaurantFragmentViewModel.callSearchNearByRestaurantApi(
                                                it.location.entity_id.toString(),
                                                it.location.entity_type,
                                                start.toString(),
                                                address[0].latitude.toString(),
                                                address[0].longitude.toString()
                                            )
                                            restaurantFragmentViewModel.livedataRestaurantSearch.observe(
                                                viewLifecycleOwner, Observer {
                                                    setRecyclerView1(it.restaurants)
                                                }
                                            )

                                        })
                                } catch (e: IOException) {
                                    e.printStackTrace()
                                }
                            }
                        }

                    })
                }
                return
            }

            else -> {

            }
        }

    }

    private fun requestLocationPermission() {

        if (checkSelfPermission(
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
                                    Log.d(TAG, "it = $it")
                                    indeterminateBar.visibility = View.GONE
                                    restaurantFragmentViewModel.callSearchNearByRestaurantApi(
                                        it.location.entity_id.toString(),
                                        it.location.entity_type,
                                        start.toString(),
                                        address[0].latitude.toString(),
                                        address[0].longitude.toString()
                                    )
                                    restaurantFragmentViewModel.livedataRestaurantSearch.observe(
                                        viewLifecycleOwner, Observer {
                                            setRecyclerView1(it.restaurants)
                                        }
                                    )

                                })
                        } catch (e: IOException) {
                            e.printStackTrace()
                        }
                    }
                }

            })
        } else {
            requestPermissions(
                arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
                REQUEST_LOCATION_PERMISSION
            )
        }

    }




    private fun setRecyclerView1(list: List<RestaurantModel>) {
        mAdapter = RestaurantAdapter1(this.requireContext(), list)
        val mLayoutManager = LinearLayoutManager(this.requireContext())
        restaurantRecyclerViewList.layoutManager = mLayoutManager
        mLayoutManager.orientation = RecyclerView.VERTICAL
        restaurantRecyclerViewList.adapter = mAdapter
        restaurantRecyclerViewList.addOnScrollListener(object:RecyclerView.OnScrollListener(){
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                val visibleItemCount = mLayoutManager.childCount
                val pastVisibleItem = mLayoutManager.findFirstCompletelyVisibleItemPosition()
                val total = mAdapter.itemCount
                if(!isLoading){
                    if(visibleItemCount+pastVisibleItem >= total){
                        start+= 20
                        mAdapter.notifyDataSetChanged()
                        requestLocationPermission()
                    }
                }
            }
        })
    }




}

