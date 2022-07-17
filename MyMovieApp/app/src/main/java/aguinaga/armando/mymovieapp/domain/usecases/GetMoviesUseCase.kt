package aguinaga.armando.mymovieapp.domain.usecases

import aguinaga.armando.mymovieapp.data.model.responses.ResponseMovies
import aguinaga.armando.mymovieapp.data.sources.repositories.MoviesRepository
import javax.inject.Inject

class GetMoviesUseCase @Inject constructor(private val moviesRepository: MoviesRepository) {

    suspend operator fun invoke(
        forzarActualizacion: Boolean,
        page: Int
    ): List<ResponseMovies.Movie>? {
        moviesRepository.forzarActualizacion = forzarActualizacion
        return moviesRepository.getMovies(page)
    }
}