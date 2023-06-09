package uz.ruzibekov.test.di

import dagger.Component
import uz.ruzibekov.test.MainActivity

@Component(modules = [AppModule::class])
interface AppComponent {

    fun inject(mainActivity: MainActivity)
}