package aguinaga.armando.mymovieapp.data.db

import aguinaga.armando.mymovieapp.data.model.responses.ResponseMovies
import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [ResponseMovies.Movie::class], version = 1)
abstract class AppDatabase: RoomDatabase() {
    abstract fun moviesDao(): MoviesDao
}