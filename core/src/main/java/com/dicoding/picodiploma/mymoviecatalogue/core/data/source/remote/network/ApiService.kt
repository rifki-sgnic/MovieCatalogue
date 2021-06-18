package com.dicoding.picodiploma.mymoviecatalogue.core.data.source.remote.network

import com.dicoding.picodiploma.core.BuildConfig
import com.dicoding.picodiploma.mymoviecatalogue.core.data.source.remote.response.CatalogueResponse
import com.dicoding.picodiploma.mymoviecatalogue.core.data.source.remote.response.DetailCatalogueResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @GET("movie/popular")
    suspend fun getMovies(
        @Query("api_key") apiKey: String = BuildConfig.API_KEY
    ): CatalogueResponse

    @GET("movie/{movie_id}")
    suspend fun getDetailMovies(
        @Path("movie_id") id: Int,
        @Query("api_key") apiKey: String = BuildConfig.API_KEY
    ): DetailCatalogueResponse

    @GET("tv/popular")
    suspend fun getTvShows(
        @Query("api_key") apiKey: String = BuildConfig.API_KEY
    ): CatalogueResponse

    @GET("tv/{tv_id}")
    suspend fun getDetailTvShows(
        @Path("tv_id") id: Int,
        @Query("api_key") apiKey: String = BuildConfig.API_KEY
    ): DetailCatalogueResponse
}