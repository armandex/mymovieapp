package aguinaga.armando.mymovieapp.data.paging

import aguinaga.armando.mymovieapp.data.model.responses.ResponseMovies
import aguinaga.armando.mymovieapp.data.sources.repositories.MoviesRepository
import aguinaga.armando.mymovieapp.ui.movies.MovieServiceApi
import androidx.paging.PagingSource
import androidx.paging.PagingState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class UserPagingDataSource @Inject constructor(
    private val moviesServiceApi: MovieServiceApi,
    private val moviesRepository: MoviesRepository
): PagingSource<Int,ResponseMovies.Movie>() {
    override fun getRefreshKey(state: PagingState<Int, ResponseMovies.Movie>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, ResponseMovies.Movie> {
        val page = params.key ?: STARTING_PAGE_INDEX
         try {
            val response = moviesServiceApi.getMovies(page)
            //response.let {
             CoroutineScope(Dispatchers.IO).launch {
                 moviesRepository.saveMovies(response.body()!!.results)
             }
           // }
             return LoadResult.Page(
                data = response.body()!!.results,
                prevKey = if (page == STARTING_PAGE_INDEX) null else page.minus(1),
                nextKey = if (response.body()!!.results.isEmpty()) null else page.plus(1)
            )

        } catch (exception: Exception) {
            return LoadResult.Error(exception)
        }
    }
    companion object {
        private const val STARTING_PAGE_INDEX = 1
    }
}