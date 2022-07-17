package aguinaga.armando.mymovieapp.ui.moviedetail

import aguinaga.armando.mymovieapp.data.model.responses.ResponseMovies
import aguinaga.armando.mymovieapp.domain.usecases.GetMovieUseCase
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieDetailViewmodel @Inject constructor(
    private val getMovieUseCase: GetMovieUseCase
) : ViewModel() {

    private val _receiveMovie = MutableLiveData<ResponseMovies.Movie>()
    private val _mostrarProgress = MutableLiveData<Boolean>()

    val getMovie: LiveData<ResponseMovies.Movie>
        get() = _receiveMovie
    val mostrarProgress: LiveData<Boolean>
        get() = _mostrarProgress

    fun obtenerMovie(idMovie: Int) = viewModelScope.launch {
        _mostrarProgress.value = true
        _receiveMovie.value = getMovieUseCase.invoke(idMovie)
        _mostrarProgress.value = false
    }
}