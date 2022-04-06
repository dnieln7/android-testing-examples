package com.dnieln7.testing.model.spacex


import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.Json

@Entity(tableName = "tb_missions")
data class Mission(
    @PrimaryKey
    @Json(name = "mission_id")
    val missionId: String,
    @Json(name = "description")
    val description: String,
    @Json(name = "mission_name")
    val missionName: String,
    @Json(name = "twitter")
    val twitter: String?,
    @Json(name = "website")
    val website: String,
    @Json(name = "wikipedia")
    val wikipedia: String
)