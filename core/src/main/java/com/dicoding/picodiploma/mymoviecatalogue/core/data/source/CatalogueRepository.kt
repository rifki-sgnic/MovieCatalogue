package com.dicoding.picodiploma.mymoviecatalogue.core.data.source

import androidx.lifecycle.asFlow
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.dicoding.picodiploma.mymoviecatalogue.core.data.source.local.LocalDataSource
import com.dicoding.picodiploma.mymoviecatalogue.core.data.source.remote.RemoteDataSource
import com.dicoding.picodiploma.mymoviecatalogue.core.data.source.remote.network.ApiResponse
import com.dicoding.picodiploma.mymoviecatalogue.core.data.source.remote.response.DetailCatalogueResponse
import com.dicoding.picodiploma.mymoviecatalogue.core.data.source.remote.response.ResultsItem
import com.dicoding.picodiploma.mymoviecatalogue.core.domain.model.Movie
import com.dicoding.picodiploma.mymoviecatalogue.core.domain.model.TvShow
import com.dicoding.picodiploma.mymoviecatalogue.core.domain.repository.ICatalogueRepository
import com.dicoding.picodiploma.mymoviecatalogue.core.utils.DataMapper
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CatalogueRepository @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource
) :
    ICatalogueRepository {
    override fun getAllMovies(): Flow<Resource<PagedList<Movie>>> =
        object : NetworkBoundResource<PagedList<Movie>, List<ResultsItem>>() {
            override fun loadFromDB(): Flow<PagedList<Movie>> {
                val config = PagedList.Config.Builder()
                    .setEnablePlaceholders(false)
                    .setInitialLoadSizeHint(5)
                    .setPageSize(5)
                    .build()

                return LivePagedListBuilder(
                    localDataSource.getMovies().map { DataMapper.mapEntitiesToDomain(it) }, config
                )
                    .build()
                    .asFlow()
            }

            override fun shouldFetch(data: PagedList<Movie>?): Boolean =
                data == null || data.isEmpty()

            override suspend fun createCall(): Flow<ApiResponse<List<ResultsItem>>> =
                remoteDataSource.getAllMovies()

            override suspend fun saveCallResult(data: List<ResultsItem>) {
                val movieList = DataMapper.mapResponsesToEntities(data)
                localDataSource.insertMovie(movieList)
            }
        }.asFlow()

    override fun getDetailMovies(movieId: Int): Flow<Resource<Movie>> =
        object : NetworkBoundResource<Movie, DetailCatalogueResponse>() {
            override fun loadFromDB(): Flow<Movie> {
                return localDataSource.getMovieById(movieId).map {
                    DataMapper.mapEntitiesToDomain(it)
                }
            }

            override fun shouldFetch(data: Movie?): Boolean =
                data?.status == null || data.genre == null

            override suspend fun createCall(): Flow<ApiResponse<DetailCatalogueResponse>> =
                remoteDataSource.getDetailMovies(movieId)

            override suspend fun saveCallResult(data: DetailCatalogueResponse) {
                val movieEntity = DataMapper.mapDetailResponsesToEntity(data)
                localDataSource.updateMovie(movieEntity)
            }
        }.asFlow()

    override fun setMovieFavorite(movie: Movie, state: Boolean) {
        val movieEntity = DataMapper.mapDomainToEntity(movie)
        CoroutineScope(Dispatchers.IO).launch {
            localDataSource.setMovieFavorite(movieEntity, state)
        }
    }

    override fun getMovieFavorite(): Flow<PagedList<Movie>> {
        val config = PagedList.Config.Builder()
            .setEnablePlaceholders(false)
            .setInitialLoadSizeHint(5)
            .setPageSize(5)
            .build()
        return LivePagedListBuilder(
            localDataSource.getMovieFavorite().map { DataMapper.mapEntitiesToDomain(it) }, config
        )
            .build()
            .asFlow()
    }

    override fun getAllTvShows(): Flow<Resource<PagedList<TvShow>>> =
        object : NetworkBoundResource<PagedList<TvShow>, List<ResultsItem>>() {
            override fun loadFromDB(): Flow<PagedList<TvShow>> {
                val config = PagedList.Config.Builder()
                    .setEnablePlaceholders(false)
                    .setInitialLoadSizeHint(5)
                    .setPageSize(5)
                    .build()

                return LivePagedListBuilder(
                    localDataSource.getTvShows().map { DataMapper.mapTvEntitiesToDomain(it) },
                    config
                )
                    .build()
                    .asFlow()
            }

            override fun shouldFetch(data: PagedList<TvShow>?): Boolean =
                data == null || data.isEmpty()

            override suspend fun createCall(): Flow<ApiResponse<List<ResultsItem>>> =
                remoteDataSource.getAllTvShows()

            override suspend fun saveCallResult(data: List<ResultsItem>) {
                val tvShowList = DataMapper.mapTvResponsesToEntities(data)
                localDataSource.insertTvShow(tvShowList)
            }
        }.asFlow()

    override fun getDetailTvShows(tvShowId: Int): Flow<Resource<TvShow>> =
        object : NetworkBoundResource<TvShow, DetailCatalogueResponse>() {
            override fun loadFromDB(): Flow<TvShow> {
                return localDataSource.getTvShowById(tvShowId).map {
                    DataMapper.mapTvEntitiesToDomain(it)
                }
            }

            override fun shouldFetch(data: TvShow?): Boolean =
                data?.status == null || data.genre == null

            override suspend fun createCall(): Flow<ApiResponse<DetailCatalogueResponse>> =
                remoteDataSource.getDetailTvShows(tvShowId)

            override suspend fun saveCallResult(data: DetailCatalogueResponse) {
                val tvShowEntity = DataMapper.mapTvDetailResponsesToEntity(data)
                localDataSource.updateTvShow(tvShowEntity)
            }
        }.asFlow()

    override fun setTvShowFavorite(tvShow: TvShow, state: Boolean) {
        val movieEntity = DataMapper.mapTvDomainToEntity(tvShow)
        CoroutineScope(Dispatchers.IO).launch {
            localDataSource.setTvShowFavorite(movieEntity, state)
        }
    }

    override fun getTvShowFavorite(): Flow<PagedList<TvShow>> {
        val config = PagedList.Config.Builder()
            .setEnablePlaceholders(false)
            .setInitialLoadSizeHint(5)
            .setPageSize(5)
            .build()
        return LivePagedListBuilder(
            localDataSource.getTvShowFavorite().map { DataMapper.mapTvEntitiesToDomain(it) }, config
        )
            .build()
            .asFlow()
    }
}