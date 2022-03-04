package com.dnieln7.testing.network.mars

import com.dnieln7.testing.model.mars.MarsPhoto
import retrofit2.http.GET

interface MarsApi {

    @GET("photos")
    suspend fun getPhotos(): List<MarsPhoto>
}