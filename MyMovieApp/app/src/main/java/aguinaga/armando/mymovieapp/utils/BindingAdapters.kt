package aguinaga.armando.mymovieapp.utils

import aguinaga.armando.mymovieapp.R
import aguinaga.armando.mymovieapp.di.NetworkModule
import aguinaga.armando.mymovieapp.ui.movies.MoviesActivity
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.squareup.picasso.Picasso
import timber.log.Timber


@BindingAdapter("imageUrl")
fun ImageView.loadImage(poster: String?) {
    if (poster.isNullOrEmpty()) return
    val anchoPantalla = MoviesActivity.anchoDispositivo
    val post = "${NetworkModule.BASE_IMAGES}${poster.replace("/", "")}"
    if (!post.isNullOrEmpty())
        Picasso.get()
            .load(post)
            .resize((anchoPantalla / 2), (anchoPantalla / 2 * 1000 / 674))
            .into(this)
    else
        Picasso.get()
            .load(R.drawable.movie2)
            .resize((anchoPantalla / 2), (anchoPantalla / 2 * 1000 / 674))
            .into(this)
}
