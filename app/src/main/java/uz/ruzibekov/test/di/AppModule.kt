package uz.ruzibekov.test.di

import android.content.Context
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import uz.ruzibekov.data.repository.CategoryRepository
import uz.ruzibekov.data.service.MainService
import uz.ruzibekov.domain.Constants
import javax.inject.Singleton


@Module
class AppModule(val context: Context) {

    @Provides
    @Singleton
    fun provideRetrofit(gson: Gson): Retrofit {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create(gson))
            .baseUrl(Constants.BASE_URL)
            .build()
    }

    @Provides
    @Singleton
    fun provideMainService(retrofit: Retrofit): MainService {
        return retrofit.create(MainService::class.java)
    }

    @Provides
    @Singleton
    fun provideCategoryRepository(service: MainService): CategoryRepository =
        CategoryRepository(service)
}