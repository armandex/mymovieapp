package aguinaga.armando.mymovieapp.di

import aguinaga.armando.mymovieapp.utils.Constantes
import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object PreferencesModule {
    val SETTINGS_PREFS_FILE_KEY = Constantes.misprefsconfiguracion
    val SECURE_PREFS_FILE_KEY = Constantes.misprefsseguridad

    @Singleton
    @Provides
    @Named("default")
    fun provideSettingsPreferences(app: Application): SharedPreferences =
        app.getSharedPreferences(SETTINGS_PREFS_FILE_KEY, Context.MODE_PRIVATE)

    @Singleton
    @Provides
    @Named("security")
    fun provideSecurePreferences(app: Application): SharedPreferences =
        app.getSharedPreferences(SECURE_PREFS_FILE_KEY, Context.MODE_PRIVATE)

}
