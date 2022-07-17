package aguinaga.armando.mymovieapp.ui.movies

import aguinaga.armando.mymovieapp.data.model.responses.ResponseMovies
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieServiceApi {

    @GET("upcoming?")
    suspend fun getMovies(
        @Query("page") value: Int
    ): Response<ResponseMovies?>

}