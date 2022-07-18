package aguinaga.armando.mymovieapp.data.sources.repositories

import aguinaga.armando.mymovieapp.data.model.responses.ResponseMovies
import aguinaga.armando.mymovieapp.data.sources.MoviesDataSource
import aguinaga.armando.mymovieapp.data.sources.local.MoviesLocalDataSource
import aguinaga.armando.mymovieapp.data.sources.remoto.MoviesRemotoDataSource
import javax.inject.Inject

class MoviesRepository @Inject constructor(
    private val moviesLocalDataSource: MoviesLocalDataSource,
    private val moviesRemotoDataSource: MoviesRemotoDataSource
) : MoviesDataSource {

    var forzarActualizacion = false

    override suspend fun getMoviesFromBackend(page: Int): ResponseMovies? {
        return obtenerPeliculasEnCarteleraRemoto(page)
    }

    override suspend fun getMovies(page: Int): List<ResponseMovies.Movie>? {
        return if (forzarActualizacion) {
            obtenerPeliculasEnCarteleraRemoto(page)?.results
        } else {
            val moviesLocal = moviesLocalDataSource.getMovies(page)
            if (moviesLocal.isNullOrEmpty()) {
                obtenerPeliculasEnCarteleraRemoto(page)?.results
            } else {
                return moviesLocal
            }
        }
    }

    override suspend fun getMovieById(idMovie: Int): ResponseMovies.Movie {
        return moviesLocalDataSource.getMovieById(idMovie)
    }

    override suspend fun saveMovies(lista: List<ResponseMovies.Movie>) {
        return moviesLocalDataSource.saveMovies(lista)
    }

    override suspend fun saveMovie(movie: ResponseMovies.Movie) {
        return moviesLocalDataSource.saveMovie(movie)
    }

    suspend fun obtenerPeliculasEnCarteleraRemoto(page: Int): ResponseMovies? {
        val peliculaRemoto = moviesRemotoDataSource.getMoviesFromBackend(page)
        peliculaRemoto?.let {
            moviesLocalDataSource.saveMovies(peliculaRemoto.results)
        }
        return peliculaRemoto
    }
}