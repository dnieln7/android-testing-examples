package com.dnieln7.testing

import android.app.Application
import com.dnieln7.testing.di.ServiceLocator
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class TestingExamplesApplication : Application() {
    val serviceLocator by lazy { ServiceLocator(applicationContext) }
}