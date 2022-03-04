package com.dnieln7.testing.network.mars

import com.dnieln7.testing.network.Api
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import junit.framework.Assert.*
import kotlinx.coroutines.runBlocking
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import okio.buffer
import okio.source
import org.junit.Before
import org.junit.Test
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class MarsApiTest {

    private val mockWebServer = MockWebServer()

    private lateinit var marsApi: MarsApi

    @Before
    fun setUp() {
        val moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()
        val url = mockWebServer.url("/")

        marsApi = Retrofit.Builder()
            .baseUrl(url)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()
            .create(MarsApi::class.java)
    }

    @Test
    fun getPhotos() {
        loadJson("mars_photos.json")

        runBlocking {
            val photos = marsApi.getPhotos()

            assertNotNull(photos)
            assertTrue("The list was empty", photos.isNotEmpty())
            assertEquals("The IDs did not match", "424905", photos[0].id)
        }
    }

    private fun loadJson(json: String) {
        val inputStream = javaClass.classLoader!!.getResourceAsStream(json)
        val buffer = inputStream.source().buffer()

        mockWebServer.enqueue(
            MockResponse()
                .setResponseCode(200)
                .setBody(buffer.readString(Charsets.UTF_8))
        )
    }
}