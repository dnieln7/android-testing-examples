package com.dnieln7.testing.utils

data class DataResponse<T>(val data: T? = null, val source: DataSource, val error: String? = null)
