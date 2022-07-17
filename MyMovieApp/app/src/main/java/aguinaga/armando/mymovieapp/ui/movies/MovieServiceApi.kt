package aguinaga.armando.mymovieapp.ui.movies

import aguinaga.armando.mymovieapp.data.model.responses.ResponseMovies
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface MovieServiceApi {

    @GET("upcoming?page={page}")
    suspend fun getMovies(
        @Path("page") page: Int
    ): Response<ResponseMovies>

}