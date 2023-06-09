package uz.ruzibekov.test


import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import uz.ruzibekov.test.app.App
import uz.ruzibekov.test.base.BasePermissionActivity
import javax.inject.Inject

class MainActivity : BasePermissionActivity() {


    private var bottomNavigation: BottomNavigationView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        (applicationContext as App).appComponent.inject(this)

//        viewModel = ViewModelProvider(this, viewModelFactory).get(MainViewModel::class.java)

        init()
    }

    private fun init() {
        initViews()

        setupNavController()

//        viewModel?.fetch()
    }

    private fun initViews() {
        bottomNavigation = findViewById(R.id.bottom_nav_view_main)
    }

    fun requestLocationPerm(city: (String) -> Unit) {
        requestLocationPermission()
        cityName = { city.invoke(it) }
    }


    private fun setupNavController() {
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.fragmentContainerView) as NavHostFragment
        val navController = navHostFragment.navController
        bottomNavigation?.setupWithNavController(navController)
    }

}