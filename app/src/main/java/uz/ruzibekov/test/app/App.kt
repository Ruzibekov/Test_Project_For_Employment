package uz.ruzibekov.test.app

import android.app.Application
import uz.ruzibekov.test.di.AppComponent
import uz.ruzibekov.test.di.AppModule
import uz.ruzibekov.test.di.DaggerAppComponent

class App : Application() {

    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()

        appComponent = DaggerAppComponent
            .builder()
            .appModule(AppModule(this))
            .build()
    }
}