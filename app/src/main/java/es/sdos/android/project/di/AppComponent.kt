package es.sdos.android.project.di

import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import es.sdos.android.project.App
import es.sdos.android.project.data.local.di.LocalModule
import es.sdos.android.project.data.remote.di.RemoteModule
import es.sdos.android.project.data.repository.di.RepositoryModule
import es.sdos.android.project.home.di.HomeFragmentBuilder
import es.sdos.android.project.launcher.di.LauncherFragmentBuilder
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AndroidInjectionModule::class,
        AppModule::class,
        LocalModule::class,
        RemoteModule::class,
        RepositoryModule::class,
        ActivityBuilder::class,
        LauncherFragmentBuilder::class,
        HomeFragmentBuilder::class
    ]
)
interface AppComponent {

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(application: App): Builder

        fun build(): AppComponent
    }

    fun inject(app: App)
}
