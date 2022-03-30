package com.dnieln7.testing.kotlin

import android.view.View
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.conflate


@ExperimentalCoroutinesApi
val View.onclickEvents: Flow<View>
    get() = callbackFlow<View> {
        val listener = View.OnClickListener { trySend(it) }
        setOnClickListener(listener)
        awaitClose { setOnClickListener(null) }
    }.conflate() // avoid multiple calls if the last value has not been processed yet