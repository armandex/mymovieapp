package aguinaga.armando.mymovieapp.ui.movies

import aguinaga.armando.mymovieapp.R
import aguinaga.armando.mymovieapp.data.model.responses.ResponseMovies
import aguinaga.armando.mymovieapp.databinding.FragmentMoviesBinding
import aguinaga.armando.mymovieapp.utils.App
import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.GridLayoutManager
import timber.log.Timber

const val REQUEST_CODE_FAVORITOS = 10

 class MoviesFragment : Fragment() {

    private lateinit var binding: FragmentMoviesBinding
    private val peliculasAdapter = MoviesAdapter()

     private val moviesViewmodel: MoviesViewmodel by activityViewModels()

     override fun onCreateView(
         inflater: LayoutInflater,
         container: ViewGroup?,
         savedInstanceState: Bundle?
     ): View? {
         binding = FragmentMoviesBinding.inflate(layoutInflater)
         return binding.root
     }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.recyclerView.adapter = peliculasAdapter
        binding.recyclerView.layoutManager = GridLayoutManager(requireContext(), 2)
        binding.recyclerView.setHasFixedSize(true)
        binding.swipeRefresh.setColorSchemeResources(R.color.colorAccent, R.color.colorPrimary)

        peliculasAdapter.setOnPeliculaClickListener { movie ->
           Bundle().let {
               it.putInt("idMovie",movie.id)
               App.goMovieDetail(requireActivity(),it)
            }
        }

        binding.swipeRefresh.setOnRefreshListener {
            obtenerPeliculas(true)
        }
        moviesViewmodel.getMovies.observe(viewLifecycleOwner) { it ->
            if (it != null) {
                peliculasAdapter.setPeliculas(it)
            } else {
                Timber.e("NULL")
            }
        }

        obtenerPeliculas(false)

        moviesViewmodel.mostrarProgress.observe(viewLifecycleOwner)  {
            binding.swipeRefresh.isRefreshing = it
        }
    }

     fun obtenerPeliculas(forzarActualizacion: Boolean){
         moviesViewmodel.obtenerMovies(forzarActualizacion, 1)
     }
}