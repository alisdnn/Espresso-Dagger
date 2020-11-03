package com.example.espressodaggerexamples.repository

import com.example.espressodaggerexamples.api.ApiService
import com.example.espressodaggerexamples.models.BlogPost
import com.example.espressodaggerexamples.models.Category
import com.example.espressodaggerexamples.util.StateEvent
import com.example.espressodaggerexamples.ui.viewmodel.state.MainViewState
import com.example.espressodaggerexamples.ui.viewmodel.state.MainViewState.*
import com.example.espressodaggerexamples.util.*
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class  MainRepositoryImpl
@Inject
constructor(
    private val apiService: ApiService
) : MainRepository{

    private val CLASS_NAME = "MainRepositoryImpl"

    override fun getBlogs(stateEvent: StateEvent, category: String): Flow<DataState<MainViewState>> {
        return flow{

            val response = safeApiCall(IO){apiService.getBlogPosts(category)}

            emit(
                object: ApiResponseHandler<MainViewState, List<BlogPost>>(
                    response = response,
                    stateEvent = stateEvent
                ) {
                    override fun handleSuccess(resultObj: List<BlogPost>): DataState<MainViewState> {
                        return DataState.data(
                            data = MainViewState(
                                listFragmentView = ListFragmentView(
                                    blogs = resultObj
                                )
                            ),
                            stateEvent = stateEvent
                        )
                    }

                }.result
            )
        }
    }

    override fun getAllBlogs(stateEvent: StateEvent): Flow<DataState<MainViewState>> {
        return flow{

            val response = safeApiCall(IO){apiService.getAllBlogPosts()}

            emit(
                object: ApiResponseHandler<MainViewState, List<BlogPost>>(
                    response = response,
                    stateEvent = stateEvent
                ) {
                    override fun handleSuccess(resultObj: List<BlogPost>): DataState<MainViewState> {
                        return DataState.data(
                            data = MainViewState(
                                listFragmentView = ListFragmentView(
                                    blogs = resultObj
                                )
                            ),
                            stateEvent = stateEvent
                        )
                    }

                }.result
            )
        }
    }

    override fun getCategories(stateEvent: StateEvent): Flow<DataState<MainViewState>> {
        return flow{

            val response = safeApiCall(IO){apiService.getCategories()}

            emit(
                object: ApiResponseHandler<MainViewState, List<Category>>(
                    response = response,
                    stateEvent = stateEvent
                ) {
                    override fun handleSuccess(resultObj: List<Category>): DataState<MainViewState> {
                        return DataState.data(
                            data = MainViewState(
                                listFragmentView = ListFragmentView(
                                    categories = resultObj
                                )
                            ),
                            stateEvent = stateEvent
                        )
                    }

                }.result
            )
        }
    }


}













