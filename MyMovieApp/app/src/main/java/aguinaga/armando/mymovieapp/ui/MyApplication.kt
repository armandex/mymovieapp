package aguinaga.armando.mymovieapp.ui

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber
import aguinaga.armando.mymovieapp.BuildConfig

@HiltAndroidApp
class MyApplication: Application() {


    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }
}