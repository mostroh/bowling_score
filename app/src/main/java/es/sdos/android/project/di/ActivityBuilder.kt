package es.sdos.android.project.di

import dagger.Module
import dagger.android.ContributesAndroidInjector
import es.sdos.android.project.NavHostActivity

@Module
abstract class ActivityBuilder {

    @ContributesAndroidInjector
    abstract fun bindNavHostActivity(): NavHostActivity

}
