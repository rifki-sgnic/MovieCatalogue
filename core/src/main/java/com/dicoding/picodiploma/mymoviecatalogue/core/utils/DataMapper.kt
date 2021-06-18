package com.dicoding.picodiploma.mymoviecatalogue.core.utils

import com.dicoding.picodiploma.mymoviecatalogue.core.data.source.local.entity.MovieEntity
import com.dicoding.picodiploma.mymoviecatalogue.core.data.source.local.entity.TvShowEntity
import com.dicoding.picodiploma.mymoviecatalogue.core.data.source.remote.response.DetailCatalogueResponse
import com.dicoding.picodiploma.mymoviecatalogue.core.data.source.remote.response.ResultsItem
import com.dicoding.picodiploma.mymoviecatalogue.core.domain.model.Movie
import com.dicoding.picodiploma.mymoviecatalogue.core.domain.model.TvShow

object DataMapper {
    fun mapEntitiesToDomain(input: MovieEntity) = Movie(
        movieId = input.movieId,
        title = input.title,
        date = input.date,
        genre = input.genre,
        description = input.description,
        tagline = input.tagline,
        status = input.status,
        imagePath = input.imagePath,
        isFavorite = input.isFavorite
    )

    fun mapTvEntitiesToDomain(input: TvShowEntity) = TvShow(
        tvShowId = input.tvShowId,
        title = input.title,
        date = input.date,
        genre = input.genre,
        description = input.description,
        tagline = input.tagline,
        status = input.status,
        imagePath = input.imagePath,
        isFavorite = input.isFavorite
    )

    fun mapResponsesToEntities(input: List<ResultsItem>): List<MovieEntity> {
        val movieList = ArrayList<MovieEntity>()

        input.map {
            val movie = MovieEntity(
                movieId = it.id,
                title = it.title,
                date = it.releaseDate,
                genre = null,
                description = it.overview,
                tagline = null,
                status = null,
                imagePath = it.posterPath,
                isFavorite = false
            )
            movieList.add(movie)
        }
        return movieList
    }

    fun mapTvResponsesToEntities(input: List<ResultsItem>): List<TvShowEntity> {
        val tvShowList = ArrayList<TvShowEntity>()

        input.map {
            val tvShow = TvShowEntity(
                tvShowId = it.id,
                title = it.name,
                date = it.firstAirDate,
                genre = null,
                description = it.overview,
                tagline = null,
                status = null,
                imagePath = it.posterPath,
                isFavorite = false
            )
            tvShowList.add(tvShow)
        }
        return tvShowList
    }

    fun mapDetailResponsesToEntity(input: DetailCatalogueResponse): MovieEntity =
        MovieEntity(
            movieId = input.id,
            title = input.title,
            date = input.releaseDate,
            genre = input.genres?.joinToString { it.name },
            description = input.overview,
            tagline = input.tagline,
            status = input.status,
            imagePath = input.posterPath,
            isFavorite = false
        )

    fun mapTvDetailResponsesToEntity(input: DetailCatalogueResponse): TvShowEntity =
        TvShowEntity(
            tvShowId = input.id,
            title = input.name,
            date = input.firstAirDate,
            genre = input.genres?.joinToString { it.name },
            description = input.overview,
            tagline = input.tagline,
            status = input.status,
            imagePath = input.posterPath,
            isFavorite = false
        )


    fun mapDomainToEntity(input: Movie) = MovieEntity(
        movieId = input.movieId,
        title = input.title,
        date = input.date,
        genre = input.genre,
        description = input.description,
        tagline = input.tagline,
        status = input.status,
        imagePath = input.imagePath,
        isFavorite = input.isFavorite
    )

    fun mapTvDomainToEntity(input: TvShow) = TvShowEntity(
        tvShowId = input.tvShowId,
        title = input.title,
        date = input.date,
        genre = input.genre,
        description = input.description,
        tagline = input.tagline,
        status = input.status,
        imagePath = input.imagePath,
        isFavorite = input.isFavorite
    )

}