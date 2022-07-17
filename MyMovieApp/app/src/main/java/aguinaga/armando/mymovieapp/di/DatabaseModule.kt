package aguinaga.armando.mymovieapp.di

import aguinaga.armando.mymovieapp.data.db.AppDatabase
import aguinaga.armando.mymovieapp.data.db.MoviesDao
import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class DatabaseModule {

    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext appContext: Context): AppDatabase {

        @Provides
        fun provideMoviesDao(appDatabase: AppDatabase): MoviesDao {
            return appDatabase.moviesDao()
        }

        return Room.databaseBuilder(
            appContext,
            AppDatabase::class.java,
            "AADatabase"
        ).build()
    }
}