package com.example.shribhagwatgita.view.fragments

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.core.content.ContextCompat
import androidx.core.view.WindowCompat
import androidx.navigation.fragment.findNavController
import com.example.shribhagwatgita.R

class splashFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        changeStatusBarColor()
        // Inflate the layout for this fragment
        Handler(Looper.getMainLooper()).postDelayed({
          findNavController().navigate(R.id.action_splashFragment_to_homeFragment)
        },3000)
        return inflater.inflate(R.layout.fragment_splash, container, false)
    }

    private fun changeStatusBarColor() {
       val window = activity?.window
        window?.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        window?.statusBarColor = ContextCompat.getColor(requireContext(), R.color.splash)
        if(window!=null){
            WindowCompat.getInsetsController(window,window.decorView).apply {
                isAppearanceLightStatusBars = true
            }
        }
    }


    // code to change status bar colour



}