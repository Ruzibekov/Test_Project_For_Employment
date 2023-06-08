package uz.ruzibekov.test.ui.home

import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.fragment.app.Fragment
import uz.ruzibekov.test.MainActivity
import uz.ruzibekov.test.R

class HomeFragment : Fragment(R.layout.fragment_home) {

    private var tvCityName: TextView? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.initViews()
        requestLocation()
    }

    private fun View.initViews(){
        tvCityName = this.findViewById(R.id.tv_city_name)
    }

    private fun requestLocation() {
        (activity as MainActivity).requestLocationPerm { city ->
            tvCityName?.text = city
        }
    }
}