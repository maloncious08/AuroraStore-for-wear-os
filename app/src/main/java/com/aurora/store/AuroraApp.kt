package com.aurora.store

import android.app.Application
import android.content.Context
import android.content.res.Configuration
import androidx.hilt.work.HiltWorkerFactory
import androidx.work.Configuration as WorkConfiguration
import dagger.hilt.android.HiltAndroidApp
import javax.inject.Inject

@HiltAndroidApp
class AuroraApp : Application(), WorkConfiguration.Provider {

    @Inject
    lateinit var workerFactory: HiltWorkerFactory

    override fun attachBaseContext(base: Context) {
        val config = Configuration(base.resources.configuration).apply {
            densityDpi = (base.resources.displayMetrics.densityDpi * 0.65f).toInt()
        }
        val context = base.createConfigurationContext(config)
        super.attachBaseContext(context)
    }

    override fun onCreate() {
        super.onCreate()
        val metrics = resources.displayMetrics
        metrics.density = metrics.density * 0.65f
        metrics.scaledDensity = metrics.scaledDensity * 0.65f
        metrics.densityDpi = (metrics.densityDpi * 0.65f).toInt()
    }

    override val workManagerConfiguration: WorkConfiguration
        get() = WorkConfiguration.Builder()
            .setWorkerFactory(workerFactory)
            .build()
}
