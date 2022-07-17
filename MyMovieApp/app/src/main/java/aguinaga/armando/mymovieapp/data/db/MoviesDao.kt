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

    @Query("SELECT * FROM Movie")
    suspend fun getAllMovies():List<ResponseMovies.Movie>
}