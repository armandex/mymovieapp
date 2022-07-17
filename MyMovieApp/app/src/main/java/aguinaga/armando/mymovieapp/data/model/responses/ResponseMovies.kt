package aguinaga.armando.mymovieapp.data.model.responses

import androidx.room.Entity
import androidx.room.PrimaryKey

data class ResponseMovies(
    private val page: Int,
    private val results: Results,
    private val total_pages: Int,
    private val total_results: Int,
) {
    data class Results(
        private val results: List<Movie>
    ) {
        @Entity
        data class Movie(
            @PrimaryKey(autoGenerate = false)
             val id: Int,
             val adult: Boolean,
             val backdrop_path: String,
             val title: String,
             val vote_average: Float,
             val release_date: String,
             val overview: String,

            )
    }
}
