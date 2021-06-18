package com.dicoding.picodiploma.mymoviecatalogue.core.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class TvShow(
    var tvShowId: Int,
    var title: String,
    var date: String,
    var genre: String? = null,
    var description: String,
    var tagline: String? = null,
    var status: String? = null,
    var imagePath: String,
    var isFavorite: Boolean
) : Parcelable