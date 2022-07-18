package aguinaga.armando.mymovieapp.ui.movies

import aguinaga.armando.mymovieapp.data.model.responses.ResponseMovies
import aguinaga.armando.mymovieapp.data.paging.UserRepository
import aguinaga.armando.mymovieapp.data.paging.UserRepositoryImpl
import aguinaga.armando.mymovieapp.domain.usecases.GetMoviesUseCase
import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import androidx.paging.map
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MoviesViewmodel @Inject constructor(
    private val getMoviesUseCase: GetMoviesUseCase,
    private val userRepositoryImpl: UserRepositoryImpl
) : ViewModel() {

    private val _receiveMovies = MutableLiveData<List<ResponseMovies.Movie>?>()
    private val _mostrarProgress = MutableLiveData<Boolean>()

    val getMovies: LiveData<List<ResponseMovies.Movie>?>
        get() = _receiveMovies

    val mostrarProgress: LiveData<Boolean>
        get() = _mostrarProgress

    val userItemsUiStates = userRepositoryImpl.getUsers()
        .map { pagingData ->
            pagingData.map { userModel -> UserItemUiState(userModel) }
        }.cachedIn(viewModelScope)

    fun obtenerMovies(forzarActualizacion: Boolean, page: Int) = viewModelScope.launch {
        _mostrarProgress.value = true
        _receiveMovies.value = getMoviesUseCase.invoke(forzarActualizacion, page)
        _mostrarProgress.value = false
    }

}