package aguinaga.armando.mymovieapp.data.sources.local

import aguinaga.armando.mymovieapp.data.db.AppDatabase
import aguinaga.armando.mymovieapp.data.model.responses.ResponseMovies
import aguinaga.armando.mymovieapp.data.sources.MoviesDataSource
import javax.inject.Inject

class MoviesLocalDataSource @Inject constructor(
    private val appDatabase: AppDatabase
)
    : MoviesDataSource {

    override suspend fun getMoviesFromBackend(page: Int)
    : ResponseMovies?{
        return null
    }

    override suspend fun getMovies(page: Int): List<ResponseMovies.Movie>? {
         return appDatabase.moviesDao().getAllMovies()
    }

    override suspend fun getMovieById(idMovie: Int): ResponseMovies.Movie {
        return appDatabase.moviesDao().getMovieById(idMovie)
    }

    override suspend fun saveMovies(lista: List<ResponseMovies.Movie>) {
        return appDatabase.moviesDao().insertMovies(lista)
    }
}