package uz.ruzibekov.test

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import uz.ruzibekov.data.repository.CategoryRepository
import javax.inject.Inject

class MainViewModel @Inject constructor(
    private val repository: CategoryRepository
) : ViewModel() {


    fun fetch(){
        viewModelScope.launch {
            repository.getCategoryList().collect {
                Log.i("RRR", it.data?.сategories?.size.toString())
            }
        }
    }
}