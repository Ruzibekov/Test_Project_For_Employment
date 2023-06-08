package uz.ruzibekov.test.ui.home

import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import uz.ruzibekov.test.MainActivity
import uz.ruzibekov.test.R
import uz.ruzibekov.test.utils.DateFactory

class HomeFragment : Fragment(R.layout.fragment_home) {

    private var tvCityName: TextView? = null
    private var tvDateAndTime: TextView? = null
    private var ivProfileImage: ImageView? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.initViews()

        requestLocation()

        initTodayDateAndTime()

        onProfileImageChange()
    }

    private fun View.initViews(){
        tvCityName = findViewById(R.id.tv_city_name)
        tvDateAndTime = findViewById(R.id.tv_date_time)
        ivProfileImage = findViewById(R.id.iv_profile_image)
    }

    private fun requestLocation() {
        (activity as MainActivity).requestLocationPerm { city ->
            tvCityName?.text = city
        }
    }

    private fun initTodayDateAndTime(){
        tvDateAndTime?.text = DateFactory.getTodayDateAndTime()
    }

    private fun onProfileImageChange(){
        val act = (activity as MainActivity)

        ivProfileImage?.setOnClickListener {
            act.launchImageSelection()
        }

        act.onImageSelected = {
            ivProfileImage?.setImageURI(it)
        }
    }
}