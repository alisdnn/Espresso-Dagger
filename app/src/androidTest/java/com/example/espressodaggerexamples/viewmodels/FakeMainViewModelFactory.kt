package com.example.espressodaggerexamples.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.espressodaggerexamples.repository.FakeMainRepositoryImpl
import com.example.espressodaggerexamples.ui.viewmodel.MainViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.InternalCoroutinesApi
import javax.inject.Inject


@ExperimentalCoroutinesApi
@InternalCoroutinesApi
class FakeMainViewModelFactory
@Inject
constructor(
    private val mainRepository: FakeMainRepositoryImpl
): ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            return MainViewModel(mainRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }


}















