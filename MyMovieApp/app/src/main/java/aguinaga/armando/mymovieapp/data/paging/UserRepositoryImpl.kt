package aguinaga.armando.mymovieapp.data.paging

import aguinaga.armando.mymovieapp.data.model.responses.ResponseMovies
import aguinaga.armando.mymovieapp.data.sources.local.MoviesLocalDataSource
import aguinaga.armando.mymovieapp.data.sources.remoto.MoviesRemotoDataSource
import aguinaga.armando.mymovieapp.data.sources.repositories.MoviesRepository
import aguinaga.armando.mymovieapp.ui.movies.MovieServiceApi
import androidx.lifecycle.asLiveData
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserRepositoryImpl @Inject constructor(
    private val userService: MovieServiceApi,
    private val moviesRepository: MoviesRepository
) : UserRepository {
    override fun getUsers(): Flow<PagingData<ResponseMovies.Movie>> {
        val flow =  Pager(
            config = PagingConfig(
                pageSize = NETWORK_PAGE_SIZE
            ),
            pagingSourceFactory = { UserPagingDataSource(userService,moviesRepository) }
        ).flow
        /*.let {
            CoroutineScope(Dispatchers.IO).launch {
                it.collect{ movie ->
                    movie.map {
                        Timber.e("${it.title}")
                        moviesRepository.saveMovie(it)
                    }
                }
            }
        }*/
        return flow
    }

    companion object {
        const val NETWORK_PAGE_SIZE = 5
    }
}