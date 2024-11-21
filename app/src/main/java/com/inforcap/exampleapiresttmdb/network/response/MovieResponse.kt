package com.inforcap.exampleapiresttmdb.network.response

import com.google.gson.annotations.SerializedName
import com.inforcap.exampleapiresttmdb.model.MovieEntity

data class MovieResponse(

    @SerializedName("results")
    var results : List<MovieEntity>

)
