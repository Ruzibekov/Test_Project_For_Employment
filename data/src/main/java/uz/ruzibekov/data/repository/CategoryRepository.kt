package uz.ruzibekov.data.repository

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import uz.ruzibekov.data.service.MainService
import uz.ruzibekov.domain.model.Resource
import uz.ruzibekov.domain.model.response.CategoryListResponse
import javax.inject.Inject

class CategoryRepository @Inject constructor(
    private val service: MainService
) {

    suspend fun getCategoryList(): Flow<Resource<CategoryListResponse>> {
        return flow {
            service.getCategories()
        }
    }
}