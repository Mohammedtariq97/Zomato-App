package com.example.zomatoapp.location

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.transition.TransitionInflater
import com.example.zomatoapp.R
import com.example.zomatoapp.model.LocationModel

class LocationsFragment : Fragment() {
    lateinit var locationSearchEditText: EditText
    lateinit var recyclerViewLocation: RecyclerView
    lateinit var currentLocation: TextView
    lateinit var locationViewModel: LocationViewModel
    lateinit var locationAdapter: LocationAdapter
    lateinit var layoutManager: LinearLayoutManager

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        locationViewModel = ViewModelProvider(this).get(LocationViewModel::class.java)
        return inflater.inflate(R.layout.fragment_locationdetail, container, false)
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
                findNavController().navigate(R.id.action_locationsFragment_to_restaurantFragment)
            }

        })
        locationSearchEditText.addTextChangedListener(object: TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                if(s.length > 2){
                    locationViewModel.callLocationApi(s.toString())
                    locationViewModel.liveDataLocationSearch.observe(viewLifecycleOwner,
                        androidx.lifecycle.Observer {
                        setRecyclerView(it.location_suggestions)
                    })
                }
            }

            override fun afterTextChanged(s: Editable?) {

            }

        })
    }

    private fun setRecyclerView(list: List<LocationModel>) {
        locationAdapter = LocationAdapter(list)
        layoutManager = LinearLayoutManager(this.requireContext())
        layoutManager.orientation = RecyclerView.VERTICAL
        recyclerViewLocation.layoutManager = layoutManager
        recyclerViewLocation.adapter = locationAdapter
    }
}