package com.example.espressodaggerexamples.di

import android.app.Application
import com.example.espressodaggerexamples.api.FakeApiService
import com.example.espressodaggerexamples.repository.FakeMainRepositoryImpl
import com.example.espressodaggerexamples.ui.*
import dagger.BindsInstance
import dagger.Component
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.InternalCoroutinesApi
import javax.inject.Singleton

@ExperimentalCoroutinesApi
@InternalCoroutinesApi
@Singleton
@Component(modules = [
    TestFragmentModule::class,
    TestViewModelModule::class,
    TestAppModule::class
])
interface TestAppComponent: AppComponent {

    val apiService: FakeApiService

    val mainRepository: FakeMainRepositoryImpl

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(app: Application): Builder

        fun build(): TestAppComponent
    }

    fun inject(detailFragmentTest: DetailFragmentTest)

    fun inject(listFragmentErrorTests: ListFragmentErrorTests)

    fun inject(mainActivityIntegrationTests: ListFragmentIntegrationTests)

    fun inject(mainNavigationTests: MainNavigationTests)

    fun inject(listFragmentNavigationTests: ListFragmentNavigationTests)

}














