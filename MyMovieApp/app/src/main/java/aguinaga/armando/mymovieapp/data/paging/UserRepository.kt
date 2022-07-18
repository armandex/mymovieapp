package aguinaga.armando.mymovieapp.data.paging

import aguinaga.armando.mymovieapp.data.model.responses.ResponseMovies
import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow


interface UserRepository {
    fun getUsers(): Flow<PagingData<ResponseMovies.Movie>>
}