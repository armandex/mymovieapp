package aguinaga.armando.mymovieapp.data.db

import aguinaga.armando.mymovieapp.data.model.responses.ResponseMovies
import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import dagger.Provides
import javax.inject.Singleton

@Dao
interface MoviesDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovies(ResponseMovies: List<ResponseMovies.Movie>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovie(Movie: ResponseMovies.Movie)

    @Query("SELECT * FROM Movie")
    suspend fun getAllMovies():List<ResponseMovies.Movie>

    @Query("SELECT * FROM Movie WHERE Movie.id=:idMovie")
    suspend fun getMovieById(idMovie: Int):ResponseMovies.Movie
}