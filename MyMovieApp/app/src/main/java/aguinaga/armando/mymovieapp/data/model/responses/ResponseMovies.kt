package aguinaga.armando.mymovieapp.data.model.responses

import androidx.room.Entity
import androidx.room.PrimaryKey

data class ResponseMovies(
    val page: Int,
    val results: List<Movie>,
    val total_pages: Int,
    val total_results: Int,
) {

    @Entity
    data class Movie(
        @PrimaryKey(autoGenerate = false)
        val id: Int,
        val adult: Boolean,
        val backdrop_path: String? = null,
        val poster_path: String? = null,
        val title: String = "untitle",
        val vote_average: Float,
        val release_date: String? = null,
        val overview: String? = null,

        )

}
