package uz.ruzibekov.data.service

import retrofit2.Response
import retrofit2.http.GET
import uz.ruzibekov.domain.model.response.CategoryListResponse

interface MainService {

    @GET("058729bd-1402-4578-88de-265481fd7d54")
    fun getCategories(): Response<CategoryListResponse>


}