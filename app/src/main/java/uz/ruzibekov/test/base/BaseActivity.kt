package uz.ruzibekov.test.base

import android.Manifest
import android.annotation.SuppressLint
import android.content.pm.PackageManager
import android.location.Geocoder
import android.location.Location
import android.net.Uri
import android.os.Build
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.google.android.gms.location.LocationServices
import uz.ruzibekov.test.R
import java.util.Locale

abstract class BasePermissionActivity : AppCompatActivity() {

    private val locationPermissionCode = 123

    var cityName: ((String) -> Unit)? = null

    fun requestLocationPermission() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
            != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(
                    Manifest.permission.ACCESS_FINE_LOCATION,
                    Manifest.permission.ACCESS_COARSE_LOCATION
                ),
                locationPermissionCode
            )
        } else {
            // Permission already granted
            getCurrentLocation()
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        if (requestCode == locationPermissionCode) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // Permission granted
                getCurrentLocation()
            } else {
                // Permission denied
                Toast.makeText(
                    this,
                    getString(R.string.location_permission_not_granted),
                    Toast.LENGTH_LONG
                ).show()
            }
        }
    }

    @SuppressLint("MissingPermission")
    private fun getCurrentLocation() {
        val fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)
        fusedLocationClient.lastLocation
            .addOnSuccessListener { location: Location? ->
                location?.let {

                    val geocoder = Geocoder(this, Locale.getDefault())

                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                        geocoder.getFromLocation(
                            location.latitude,
                            location.longitude,
                            1
                        ) { addresses ->
                            cityName?.invoke(
                                addresses[0]?.locality ?: getString(R.string.unknown)
                            )
                        }
                    } else {
                        val addresses = geocoder.getFromLocation(
                            location.latitude,
                            location.longitude,
                            1
                        )
                        cityName?.invoke(addresses?.get(0)?.locality ?: getString(R.string.unknown))
                    }
                }
            }
            .addOnFailureListener { exception: Exception ->
                Toast.makeText(
                    this,
                    getString(R.string.location_permission_not_granted),
                    Toast.LENGTH_LONG
                ).show()
            }

    }


    private val launcher = registerForActivityResult(
        ActivityResultContracts.GetContent()
    ) { uri: Uri? ->
        uri?.let {
            onImageSelected?.let { uri -> uri(it) }
        }
    }

    open var onImageSelected: ((Uri) -> Unit)? = null

    fun launchImageSelection() {
        launcher.launch("image/*")
    }
}