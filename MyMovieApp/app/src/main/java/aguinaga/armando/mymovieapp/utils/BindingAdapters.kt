package aguinaga.armando.mymovieapp.utils

import aguinaga.armando.mymovieapp.di.NetworkModule
import aguinaga.armando.mymovieapp.ui.movies.MoviesActivity
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.squareup.picasso.Picasso
import timber.log.Timber


@BindingAdapter("imageUrl")
fun ImageView.loadImage(url: String?) {
    if (url.isNullOrEmpty()) return
    val anchoPantalla = MoviesActivity.anchoDispositivo
    val url2 = "${NetworkModule.BASE_IMAGES}${url.replace("/","")}"
    Timber.e("url2 $url2")
    Picasso.get()
        .load(url2)
        .resize((anchoPantalla / 2) , (anchoPantalla / 2 * 1000 / 674) )
        .into(this)
}
