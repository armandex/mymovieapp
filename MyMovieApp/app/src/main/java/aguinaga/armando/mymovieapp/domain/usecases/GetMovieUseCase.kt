package aguinaga.armando.mymovieapp.domain.usecases

import aguinaga.armando.mymovieapp.data.model.responses.ResponseMovies
import aguinaga.armando.mymovieapp.data.sources.repositories.MoviesRepository
import javax.inject.Inject

class GetMovieUseCase @Inject constructor(private val moviesRepository: MoviesRepository){

    suspend operator fun invoke(
        idMovie: Int
    ): ResponseMovies.Movie {
        return moviesRepository.getMovieById(idMovie)
    }
}