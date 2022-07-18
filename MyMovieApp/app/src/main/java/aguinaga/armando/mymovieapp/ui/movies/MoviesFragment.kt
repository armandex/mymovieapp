package aguinaga.armando.mymovieapp.ui.movies

import aguinaga.armando.mymovieapp.R
import aguinaga.armando.mymovieapp.databinding.FragmentMoviesBinding
import aguinaga.armando.mymovieapp.utils.collect
import aguinaga.armando.mymovieapp.utils.collectLast
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.paging.LoadState
import androidx.paging.PagingData
import androidx.recyclerview.widget.GridLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import es.dmoral.toasty.Toasty
import kotlinx.coroutines.flow.distinctUntilChangedBy
import kotlinx.coroutines.flow.map
import timber.log.Timber
import javax.inject.Inject
@AndroidEntryPoint
class MoviesFragment : Fragment() {

    private lateinit var binding: FragmentMoviesBinding
    @Inject
    lateinit var moviesAdapter: MoviesAdapter
    private val moviesViewmodel: MoviesViewmodel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMoviesBinding.inflate(layoutInflater)
        //binding = DataBindingUtil.setContentView(requireActivity(), R.layout.fragment_movies)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        //binding.recyclerView.adapter = moviesAdapter
        binding.recyclerView.layoutManager = GridLayoutManager(requireContext(), 2)
        binding.recyclerView.setHasFixedSize(true)
        binding.swipeRefresh.setColorSchemeResources(R.color.colorAccent, R.color.colorPrimary)
        moviesAdapter.setOnPeliculaClickListener { idMovie ->
            Bundle().let {
                it.putInt("idMovie", idMovie)
                val action =
                    MoviesFragmentDirections.actionMoviesFragmentToMovieDetailFragment(idMovie)
                findNavController().navigate(action)
            }
        }

        setAdapter()
        collectLast(moviesViewmodel.userItemsUiStates, ::setMovies)
        binding.swipeRefresh.setOnRefreshListener {
            if (MoviesActivity.isInternetEnabled) {
                obtenerPeliculas(true)
            } else {
                binding.swipeRefresh.isRefreshing = false
                //requireContext().run {
                    Toasty.error(requireContext(), "Solucione su conexion por favor").show()
                //}
            }
            Timber.e("isInternetEnabled: " + MoviesActivity.isInternetEnabled)
        }
        /*moviesViewmodel.getMovies.observe(viewLifecycleOwner) { it ->
            if (it != null) {
                moviesAdapter.setPeliculas(it)
            } else {
                Toasty.error(requireContext(), "Ocurrió un incoveniente, vuelva a intentarlo").show()
                Timber.e("NULL")
            }
        }*/

        //obtenerPeliculas(false)

        /*moviesViewmodel.mostrarProgress.observe(viewLifecycleOwner) {
            binding.swipeRefresh.isRefreshing = it
        }*/
        /*moviesViewmodel.onclick({

        })*/
    }
   /* private fun setBinding() {
        binding = DataBindingUtil.inflate(layoutInflater)
    }*/
    private fun setAdapter() {
        collect(flow = moviesAdapter.loadStateFlow
            .distinctUntilChangedBy { it.source.refresh }
            .map { it.refresh },
            action = ::setUsersUiState
        )
        binding.recyclerView.adapter = moviesAdapter//.withLoadStateFooter(FooterAdapter(userAdapter::retry))
    }
    private fun setUsersUiState(loadState: LoadState) {
       /* binding.executeWithAction {
            usersUiState = UsersUiState(loadState)
        }*/
    }

    private suspend fun setMovies(userItemsPagingData: PagingData<UserItemUiState>) {
        moviesAdapter.submitData(userItemsPagingData)
    }
    fun obtenerPeliculas(forzarActualizacion: Boolean) {
        moviesViewmodel.obtenerMovies(forzarActualizacion, 1)
    }
}