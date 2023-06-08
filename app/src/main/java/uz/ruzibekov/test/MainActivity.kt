package uz.ruzibekov.test


import android.os.Bundle
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import uz.ruzibekov.test.base.BasePermissionActivity

class MainActivity : BasePermissionActivity() {

    private var bottomNavigation: BottomNavigationView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        init()
    }

    fun requestLocationPerm(city: (String) -> Unit) {
        requestLocationPermission()
        cityName = { city.invoke(it) }
    }

    private fun init() {
        bottomNavigation = findViewById(R.id.bottom_nav_view_main)
        setupNavController()
    }


    private fun setupNavController() {
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.fragmentContainerView) as NavHostFragment
        val navController = navHostFragment.navController
        bottomNavigation?.setupWithNavController(navController)
    }
}