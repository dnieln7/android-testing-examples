package com.dnieln7.testing.model.cats

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tb_cats")
data class Cat(

    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    @ColumnInfo(name = "name")
    val name: String,
    @ColumnInfo(name = "description")
    val description: String?,
    @ColumnInfo(name = "dog_friendly", defaultValue = "false")
    val dogFriendly: Boolean,
    @ColumnInfo(name = "child_friendly", defaultValue = "false")
    val childFriendly: Boolean,
    @ColumnInfo(name = "stranger_friendly", defaultValue = "false")
    val strangerFriendly: Boolean
)