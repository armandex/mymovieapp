package aguinaga.armando.mymovieapp.ui.moviedetail

import aguinaga.armando.mymovieapp.R
import aguinaga.armando.mymovieapp.databinding.FragmentMovieDetailBinding
import aguinaga.armando.mymovieapp.di.NetworkModule
import aguinaga.armando.mymovieapp.ui.movies.MoviesActivity
import aguinaga.armando.mymovieapp.ui.movies.MoviesFragmentDirections
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
import timber.log.Timber

class MovieDetailFragment : Fragment() {

    private var _binding: FragmentMovieDetailBinding? = null
    private val binding get() = _binding!!
    private val movieDetailViewmodel: MovieDetailViewmodel by activityViewModels()
    //val args: MoviesFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMovieDetailBinding.inflate(layoutInflater)
        binding.viewModel = movieDetailViewmodel
        binding.lifecycleOwner = viewLifecycleOwner
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initialize()
        observers()

        (activity as MoviesActivity).showArrow(true)
    }

    private fun initialize() {
        val idMovie = arguments?.getInt("idMovie")!!
        movieDetailViewmodel.obtenerMovie(idMovie)
    }

    private fun observers() {
        movieDetailViewmodel.getMovie.observe(viewLifecycleOwner) {
            Timber.e("title: ${it.title}")
            if (!it.backdrop_path.isNullOrEmpty()) {
                val url = "${NetworkModule.BASE_IMAGES}${it.backdrop_path.replace("/", "")}"
                Picasso.get()
                    .load(url)
                    .into(binding.imgMovieDetail)
            } else if (!it.poster_path.isNullOrEmpty()) {
                val url = "${NetworkModule.BASE_IMAGES}${it.poster_path.replace("/", "")}"
                Picasso.get()
                    .load(url)
                    .into(binding.imgMovieDetail)
            } else {
                Picasso.get()
                    .load(R.drawable.movie1)
                    .into(binding.imgMovieDetail)
            }
        }
        movieDetailViewmodel.mostrarProgress.observe(viewLifecycleOwner) {
            if (it)
                DialogUtils.progressWithTitle(requireContext(), false, "Cargando...")
            else
                DialogUtils.closeProgressWithTitle()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}