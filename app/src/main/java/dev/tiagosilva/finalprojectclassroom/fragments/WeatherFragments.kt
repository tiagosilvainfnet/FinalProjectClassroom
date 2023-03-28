package dev.tiagosilva.finalprojectclassroom.fragments

import android.os.Bundle
import android.Manifest
import android.content.pm.PackageManager
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import dev.tiagosilva.finalprojectclassroom.R

class WeatherFragments : Fragment() {
    companion object{
        private const val REQUEST_LOCATION_PERMISSION = 1
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_weather, container, false)

        if(ContextCompat.checkSelfPermission(requireContext(), Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED){
            getLocation(view)
        }else{
            requestPermissions(arrayOf(Manifest.permission.ACCESS_FINE_LOCATION), REQUEST_LOCATION_PERMISSION)
        }

        return view;
    }

    private fun getLocation(view: View){

    }
}