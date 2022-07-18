package aguinaga.armando.mymovieapp.data.sources.remoto

import aguinaga.armando.mymovieapp.data.model.responses.ResponseMovies
import aguinaga.armando.mymovieapp.data.sources.MoviesDataSource
import aguinaga.armando.mymovieapp.ui.movies.MovieServiceApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import timber.log.Timber
import javax.inject.Inject

class MoviesRemotoDataSource @Inject constructor(
    private val moviesServiceApi: MovieServiceApi
): MoviesDataSource {

    override suspend fun getMoviesFromBackend(
        page: Int
    ): ResponseMovies?  = withContext(Dispatchers.IO) {
        val response = moviesServiceApi.getMovies(page)
        Timber.e("getMoviesFromBackend"+response.body()?.results?.get(0)?.title)
        if (response.isSuccessful && response.code() == 200) {
            return@withContext response.body()
        } else {
            return@withContext null
        }
    }

    override suspend fun getMovies(page: Int): List<ResponseMovies.Movie>? {
        return null
    }

    override suspend fun getMovieById(page: Int): ResponseMovies.Movie? {
        return null
    }

    override suspend fun saveMovies(lista: List<ResponseMovies.Movie>) {

    }

    override suspend fun saveMovie(movie: ResponseMovies.Movie) {

    }
}