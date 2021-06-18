package com.dicoding.picodiploma.mymoviecatalogue.core.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class DetailCatalogueResponse(

	@field:SerializedName("overview")
	val overview: String,

	@field:SerializedName("tagline")
	val tagline: String,

	@field:SerializedName("release_date")
	val releaseDate: String,

	@field:SerializedName("first_air_date")
	val firstAirDate: String,

	@field:SerializedName("genres")
	val genres: List<GenresItem>? = null,

	@field:SerializedName("id")
	val id: Int,

	@field:SerializedName("title")
	val title: String,

	@field:SerializedName("name")
	val name: String,

	@field:SerializedName("poster_path")
	val posterPath: String,

	@field:SerializedName("status")
	val status: String
)

data class GenresItem(

	@field:SerializedName("name")
	val name: String,

	@field:SerializedName("id")
	val id: Int
)
