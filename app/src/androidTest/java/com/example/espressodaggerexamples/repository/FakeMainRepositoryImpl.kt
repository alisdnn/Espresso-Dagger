package com.example.espressodaggerexamples.repository

import com.example.espressodaggerexamples.api.FakeApiService
import com.example.espressodaggerexamples.models.BlogPost
import com.example.espressodaggerexamples.models.Category
import com.example.espressodaggerexamples.ui.viewmodel.state.MainViewState
import com.example.espressodaggerexamples.util.ApiResponseHandler
import com.example.espressodaggerexamples.util.DataState
import com.example.espressodaggerexamples.util.StateEvent
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import javax.inject.Singleton


/**
 * The only difference between this and the real MainRepositoryImpl is the ApiService is
 * fake and it's not being injected so I can change it at runtime.
 * That way I can alter the FakeApiService for each individual test.
 */
@Singleton
class FakeMainRepositoryImpl
@Inject
constructor(): MainRepository{

    private val CLASS_NAME: String = "FakeMainRepositoryImpl"

    lateinit var apiService: FakeApiService

    private fun throwExceptionIfApiServiceNotInitialzied(){
        if(!::apiService.isInitialized){
            throw UninitializedPropertyAccessException(
                "Did you forget to set the ApiService in FakeMainRepositoryImpl?"
            )
        }
    }

    @Throws(UninitializedPropertyAccessException::class)
    override fun getBlogs(stateEvent: StateEvent, category: String): Flow<DataState<MainViewState>> {
        throwExceptionIfApiServiceNotInitialzied()
        return flow{

            val response = safeApiCall(Dispatchers.IO){apiService.getBlogPosts(category)}

            emit(
                object: ApiResponseHandler<MainViewState, List<BlogPost>>(
                    response = response,
                    stateEvent = stateEvent
                ) {
                    override fun handleSuccess(resultObj: List<BlogPost>): DataState<MainViewState> {
                        return DataState.data(
                            data = MainViewState(
                                listFragmentView = MainViewState.ListFragmentView(
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

    @Throws(UninitializedPropertyAccessException::class)
    override fun getAllBlogs(stateEvent: StateEvent): Flow<DataState<MainViewState>> {
        throwExceptionIfApiServiceNotInitialzied()
        return flow{

            val response = safeApiCall(Dispatchers.IO){apiService.getAllBlogPosts()}

            emit(
                object: ApiResponseHandler<MainViewState, List<BlogPost>>(
                    response = response,
                    stateEvent = stateEvent
                ) {
                    override fun handleSuccess(resultObj: List<BlogPost>): DataState<MainViewState> {
                        return DataState.data(
                            data = MainViewState(
                                listFragmentView = MainViewState.ListFragmentView(
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

    @Throws(UninitializedPropertyAccessException::class)
    override fun getCategories(stateEvent: StateEvent): Flow<DataState<MainViewState>> {
        throwExceptionIfApiServiceNotInitialzied()
        return flow{

            val response = safeApiCall(Dispatchers.IO){apiService.getCategories()}

            emit(
                object: ApiResponseHandler<MainViewState, List<Category>>(
                    response = response,
                    stateEvent = stateEvent
                ) {
                    override fun handleSuccess(resultObj: List<Category>): DataState<MainViewState> {
                        return DataState.data(
                            data = MainViewState(
                                listFragmentView = MainViewState.ListFragmentView(
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