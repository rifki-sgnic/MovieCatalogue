package com.dicoding.picodiploma.mymoviecatalogue.core.data.source.remote

import android.util.Log
import com.dicoding.picodiploma.mymoviecatalogue.core.data.source.remote.network.ApiResponse
import com.dicoding.picodiploma.mymoviecatalogue.core.data.source.remote.network.ApiService
import com.dicoding.picodiploma.mymoviecatalogue.core.data.source.remote.response.DetailCatalogueResponse
import com.dicoding.picodiploma.mymoviecatalogue.core.data.source.remote.response.ResultsItem
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RemoteDataSource @Inject constructor(private val apiService: ApiService) {

    fun getAllMovies(): Flow<ApiResponse<List<ResultsItem>>> {
        return flow {
            try {
                val response = apiService.getMovies()
                val result = response.results
                if (result.isNotEmpty()) {
                    emit(ApiResponse.Success(response.results))
                } else {
                    emit(ApiResponse.Empty)
                }
            } catch (e: Exception) {
                emit(ApiResponse.Error(e.toString()))
                Log.e("RemoteDataSource", e.toString())
            }
        }.flowOn(Dispatchers.IO)
    }

    fun getAllTvShows(): Flow<ApiResponse<List<ResultsItem>>> {

        return flow {
            try {
                val response = apiService.getTvShows()
                val result = response.results
                if (result.isNotEmpty()) {
                    emit(ApiResponse.Success(response.results))
                } else {
                    emit(ApiResponse.Empty)
                }
            } catch (e: Exception) {
                emit(ApiResponse.Error(e.toString()))
                Log.e("RemoteDataSource", e.toString())
            }
        }.flowOn(Dispatchers.IO)
    }

    fun getDetailMovies(id: Int): Flow<ApiResponse<DetailCatalogueResponse>> {
        return flow {
            try {
                val response = apiService.getDetailMovies(id)
                emit(ApiResponse.Success(response))
            } catch (e: Exception) {
                emit(ApiResponse.Error(e.toString()))
                Log.e("RemoteDataSource", e.toString())
            }
        }.flowOn(Dispatchers.IO)
    }

    fun getDetailTvShows(id: Int): Flow<ApiResponse<DetailCatalogueResponse>> {
        return flow {
            try {
                val response = apiService.getDetailTvShows(id)
                emit(ApiResponse.Success(response))
            } catch (e: Exception) {
                emit(ApiResponse.Error(e.toString()))
                Log.e("RemoteDataSource", e.toString())
            }
        }.flowOn(Dispatchers.IO)
    }
}