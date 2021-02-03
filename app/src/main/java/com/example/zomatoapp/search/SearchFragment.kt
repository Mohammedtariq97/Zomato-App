package com.example.zomatoapp.search

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.recyclerview.widget.RecyclerView
import com.example.zomatoapp.R

class SearchFragment : Fragment() {
    lateinit var searchRestaurantEditText:EditText
    lateinit var searchRecyclerView:RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_search, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        searchRestaurantEditText = view.findViewById(R.id.searchRestaurantEditText)
        searchRecyclerView = view.findViewById(R.id.searchRecyclerView)
    }

}