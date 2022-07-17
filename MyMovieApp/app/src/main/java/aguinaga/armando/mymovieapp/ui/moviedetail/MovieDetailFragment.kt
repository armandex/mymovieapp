package aguinaga.armando.mymovieapp.ui.moviedetail

import aguinaga.armando.mymovieapp.R
import aguinaga.armando.mymovieapp.databinding.FragmentMovieDetailBinding
import aguinaga.armando.mymovieapp.di.NetworkModule
import aguinaga.armando.mymovieapp.ui.movies.MoviesActivity
import aguinaga.armando.mymovieapp.utils.DialogUtils
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_drawer.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers

class MovieDetailFragment : Fragment() {

    private lateinit var binding: FragmentMovieDetailBinding
    private val movieDetailViewmodel: MovieDetailViewmodel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMovieDetailBinding.inflate(layoutInflater)
        binding.viewModel = movieDetailViewmodel
        binding.lifecycleOwner = viewLifecycleOwner
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initialize()
        observers()
    }

    private fun initialize() {
        Bundle().let {
            val idMovie = arguments?.getInt("idMovie")!!
            movieDetailViewmodel.obtenerMovie(idMovie)
        }
        //if ()
        //(activity as MoviesActivity).supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        //(activity as MoviesActivity).supportActionBar!!.setDisplayShowHomeEnabled(true)


    }

    private fun observers() {
        movieDetailViewmodel.getMovie.observe(viewLifecycleOwner) {
            val url = "${NetworkModule.BASE_IMAGES}${it.backdrop_path.replace("/", "")}"
            Picasso.get()
                .load(url)
                //.fit()
                //.centerCrop()
                .into(binding.imgMovieDetail)
            //drawer_title.text = "${it.title}"

        }
        movieDetailViewmodel.mostrarProgress.observe(viewLifecycleOwner) {
            if (it)
                DialogUtils.progressWithTitle(requireContext(), false, "Cargando...")
            else
                DialogUtils.closeProgressWithTitle()
        }
    }

}