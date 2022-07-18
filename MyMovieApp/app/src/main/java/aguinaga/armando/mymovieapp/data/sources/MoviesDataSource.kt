package aguinaga.armando.mymovieapp.data.sources

import aguinaga.armando.mymovieapp.data.model.responses.ResponseMovies

interface MoviesDataSource {

    suspend fun getMoviesFromBackend(
        page: Int
    ): ResponseMovies?

    suspend fun getMovies(
        page: Int
    ): List<ResponseMovies.Movie>?

    suspend fun getMovieById(
        idMovie: Int
    ): ResponseMovies.Movie?

    suspend fun saveMovies(lista: List<ResponseMovies.Movie>)

    suspend fun saveMovie(movie: ResponseMovies.Movie)

}