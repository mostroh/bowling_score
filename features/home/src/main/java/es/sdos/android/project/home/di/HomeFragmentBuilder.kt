package es.sdos.android.project.home.di

import dagger.Module
import dagger.android.ContributesAndroidInjector
import es.sdos.android.project.home.ui.fragment.GameFragment
import es.sdos.android.project.home.ui.fragment.HomeFragment
import es.sdos.android.project.home.ui.fragment.ScoreHistoryFragment

@Module
abstract class HomeFragmentBuilder {

    @ContributesAndroidInjector
    internal abstract fun bindHomeFragment(): HomeFragment

    @ContributesAndroidInjector
    internal abstract fun bindScoreHistoryFragment(): ScoreHistoryFragment

    @ContributesAndroidInjector
    internal abstract fun bindGameFragment(): GameFragment
}
