package com.example.zomatoapp.splash

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toolbar
import androidx.navigation.fragment.findNavController
import com.example.zomatoapp.R
import com.google.android.material.bottomnavigation.BottomNavigationView

class SplashFragment : Fragment() {

    lateinit var handler: Handler
    lateinit var runnable: Runnable
    lateinit var splashImage: ImageView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_splash, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        splashImage = view.findViewById(R.id.splashImage)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        splashImage.setImageResource(R.drawable.splashimg)
        handler = Handler(Looper.getMainLooper())
        runnable = Runnable {
            findNavController().navigate(R.id.action_splashFragment_to_restaurantFragment)
        }
        handler.postDelayed(runnable,4000)
    }
}