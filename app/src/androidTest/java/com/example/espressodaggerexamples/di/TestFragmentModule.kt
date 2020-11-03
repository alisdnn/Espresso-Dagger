package com.example.espressodaggerexamples.di

import androidx.fragment.app.FragmentFactory
import com.example.espressodaggerexamples.fragments.FakeMainFragmentFactory
import com.example.espressodaggerexamples.util.GlideManager
import com.example.espressodaggerexamples.viewmodels.FakeMainViewModelFactory
import dagger.Module
import dagger.Provides
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.InternalCoroutinesApi
import javax.inject.Singleton

@ExperimentalCoroutinesApi
@InternalCoroutinesApi
@Module
object TestFragmentModule {

    @JvmStatic
    @Singleton
    @Provides
    fun provideMainFragmentFactory(
        viewModelFactory: FakeMainViewModelFactory,
        glideManager: GlideManager
    ): FragmentFactory {
        return FakeMainFragmentFactory(viewModelFactory, glideManager)
    }
}








