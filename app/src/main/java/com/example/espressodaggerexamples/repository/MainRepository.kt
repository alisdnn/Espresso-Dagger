package com.example.espressodaggerexamples.repository

import com.example.espressodaggerexamples.util.StateEvent
import com.example.espressodaggerexamples.ui.viewmodel.state.MainViewState
import com.example.espressodaggerexamples.util.DataState
import kotlinx.coroutines.flow.Flow

interface MainRepository{

    fun getBlogs(stateEvent: StateEvent, category: String): Flow<DataState<MainViewState>>

    fun getAllBlogs(stateEvent: StateEvent): Flow<DataState<MainViewState>>

    fun getCategories(stateEvent: StateEvent): Flow<DataState<MainViewState>>
}