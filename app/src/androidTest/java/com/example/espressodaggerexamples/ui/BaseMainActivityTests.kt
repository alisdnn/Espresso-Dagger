package com.example.espressodaggerexamples.ui

import com.example.espressodaggerexamples.TestBaseApplication
import com.example.espressodaggerexamples.api.FakeApiService
import com.example.espressodaggerexamples.di.TestAppComponent
import com.example.espressodaggerexamples.repository.FakeMainRepositoryImpl
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.InternalCoroutinesApi

/**
 * All tests extend this base class for easy configuration of fake Repository
 * and fake ApiService.
 */
@ExperimentalCoroutinesApi
@InternalCoroutinesApi
abstract class BaseMainActivityTests{

    fun configureFakeApiService(
        blogsDataSource: String? = null,
        categoriesDataSource: String? = null,
        networkDelay: Long? = null,
        application: TestBaseApplication
    ): FakeApiService {
        val apiService = (application.appComponent as TestAppComponent).apiService
        blogsDataSource?.let { apiService.blogPostsJsonFileName = it }
        categoriesDataSource?.let { apiService.categoriesJsonFileName = it }
        networkDelay?.let { apiService.networkDelay = it }
        return apiService
    }

    fun configureFakeRepository(
        apiService: FakeApiService,
        application: TestBaseApplication
    ): FakeMainRepositoryImpl {
        val mainRepository = (application.appComponent as TestAppComponent).mainRepository
        mainRepository.apiService = apiService
        return mainRepository
    }


    abstract fun injectTest(application: TestBaseApplication)
}














