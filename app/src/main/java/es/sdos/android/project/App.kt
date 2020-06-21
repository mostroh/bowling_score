package es.sdos.android.project

import android.app.Application
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import es.sdos.android.project.di.DaggerAppComponent
import javax.inject.Inject

open class App : Application(), HasAndroidInjector {

    @Inject
    lateinit var androidInjector: DispatchingAndroidInjector<Any>

    override fun androidInjector(): AndroidInjector<Any> = androidInjector

    override fun onCreate() {
        super.onCreate()
        configureDi()
    }

    // CONFIGURATION ---
    open fun configureDi() {
        DaggerAppComponent.builder()
            .application(this)
            .build()
            .inject(this)
    }
}