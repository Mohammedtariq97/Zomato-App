package com.example.zomatoapp.search

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.zomatoapp.R
import com.example.zomatoapp.model.RestaurantModel
import com.example.zomatoapp.restaurants.RestaurantAdapter

class SearchFragment : Fragment() {
    lateinit var searchRestaurantEditText:EditText
    lateinit var searchRecyclerView:RecyclerView
    lateinit var searchViewModel: SearchViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        searchViewModel = ViewModelProvider(this).get(SearchViewModel::class.java)
        return inflater.inflate(R.layout.fragment_search, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        searchRestaurantEditText = view.findViewById(R.id.searchRestaurantEditText)
        searchRecyclerView = view.findViewById(R.id.searchRecyclerView)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        searchViewModel.liveDataSearchRestaurants.observe(viewLifecycleOwner, Observer {
            setRecyclerView(it.restaurants)
        })
        searchRestaurantEditText.addTextChangedListener(object:TextWatcher{
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if(s?.length!! > 1){
                    searchViewModel.callSearchRestaurantApi("11332",s.toString())
                }
            }

            override fun afterTextChanged(s: Editable?) {

            }

        })
    }

    private fun setRecyclerView(list: List<RestaurantModel>) {
        val mAdapter = RestaurantAdapter(this.requireContext(), list)
        val mLayoutManager = LinearLayoutManager(this.requireContext())
        searchRecyclerView.layoutManager = mLayoutManager
        mLayoutManager.orientation = RecyclerView.VERTICAL
        searchRecyclerView.itemAnimator = DefaultItemAnimator()
        searchRecyclerView.adapter = mAdapter
    }

}