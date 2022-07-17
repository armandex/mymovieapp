package aguinaga.armando.mymovieapp.data.db

import aguinaga.armando.mymovieapp.data.model.responses.ResponseMovies
import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface MoviesDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMovies(ResponseMovies: List<ResponseMovies.Results.Movie>)

    @Query("SELECT * FROM Movie")
    fun getAllMovies():LiveData<List<ResponseMovies.Results.Movie>>
}