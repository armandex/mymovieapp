package aguinaga.armando.mymovieapp.ui.movies

import aguinaga.armando.mymovieapp.data.model.responses.ResponseMovies
import aguinaga.armando.mymovieapp.utils.BaseUiState

data class UserItemUiState(private val userModel: ResponseMovies.Movie) : BaseUiState() {


    fun getId() = userModel.id

    fun getImageUrl() = userModel.poster_path

    fun getTitle() = userModel.title

    fun getOverview() = userModel.overview

    fun getVoteAverage() = userModel.vote_average

    fun getReleaseDdate() = userModel.release_date

}