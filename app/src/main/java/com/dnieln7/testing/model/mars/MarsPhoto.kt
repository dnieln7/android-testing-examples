package com.dnieln7.testing.model.mars

import com.squareup.moshi.Json

data class MarsPhoto(val id: String, @Json(name = "img_src") val url: String)
