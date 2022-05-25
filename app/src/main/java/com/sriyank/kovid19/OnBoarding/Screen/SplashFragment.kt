package com.sriyank.kovid19.OnBoarding.Screen

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.findNavController
import com.sriyank.kovid19.APIAuth.MyConfig
import com.sriyank.kovid19.R


class SplashFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

            Handler().postDelayed({

                if(checkLoggedIn()){
                    findNavController().navigate(R.id.action_splashFragment_to_homeActivity)
                }
                else if(onBoardingFinished()){
                    findNavController().navigate(R.id.action_splashFragment_to_loginActivity)
                }else{
                    findNavController().navigate(R.id.action_splashFragment_to_viewPagerFragment)
                }        },3500)
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_splash, container, false)
    }

    private fun onBoardingFinished(): Boolean{
        val sharedPref = requireActivity().getSharedPreferences("onBoarding", Context.MODE_PRIVATE)
        return sharedPref.getBoolean("Finished", false)
    }

    private fun checkLoggedIn() : Boolean{
        // check if user is logged in
        val prefs = requireActivity().getSharedPreferences(
            MyConfig.SHARED_PREFS_FILENAME,
            AppCompatActivity.MODE_PRIVATE
        )
        val token = prefs.getString("token", null) ?: return false
        return true
    }

}