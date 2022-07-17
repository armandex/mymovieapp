package aguinaga.armando.mymovieapp.ui.movies

import aguinaga.armando.mymovieapp.R
import aguinaga.armando.mymovieapp.data.model.responses.ResponseMovies
import aguinaga.armando.mymovieapp.di.NetworkModule
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_pelicula.view.*
import timber.log.Timber

class MoviesAdapter : RecyclerView.Adapter<MoviesAdapter.ViewHolder>() {

    private var movies: List<ResponseMovies.Movie>? = null
    private var onPeliculaClickListener: ((ResponseMovies.Movie) -> Unit)? = null
    private var anchoPantalla: Int = 0

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_pelicula, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = movies?.size ?: 0

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        movies?.let {
            holder.bind(it[position], onPeliculaClickListener)
        }
    }

    fun setPeliculas(peliculas: List<ResponseMovies.Movie>) {
        this.movies = peliculas
        notifyDataSetChanged()
    }

    fun setOnPeliculaClickListener(listener: ((ResponseMovies.Movie) -> Unit)) {
        this.onPeliculaClickListener = listener
    }

    class ViewHolder(override val containerView: View) : RecyclerView.ViewHolder(containerView),
        LayoutContainer {

        fun bind(movie: ResponseMovies.Movie, onPeliculaClickListener: ((ResponseMovies.Movie) -> Unit)?) {
            val anchoPantalla = containerView.context.resources.displayMetrics.widthPixels
            val url = "${NetworkModule.BASE_IMAGES}${movie.backdrop_path.replace("/","")}"

            Picasso.get()
                .load(url)
                .resize(anchoPantalla / 2, anchoPantalla / 2 * 1000 / 674)
                .into(containerView.imgPelicula)

            containerView.setOnClickListener {
                onPeliculaClickListener?.invoke(movie)
            }
        }
    }
}