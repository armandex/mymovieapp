package aguinaga.armando.mymovieapp.ui.movies

import aguinaga.armando.mymovieapp.R
import aguinaga.armando.mymovieapp.data.model.responses.ResponseMovies
import aguinaga.armando.mymovieapp.databinding.ItemMovieBinding
import aguinaga.armando.mymovieapp.di.NetworkModule
import aguinaga.armando.mymovieapp.utils.executeWithAction
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.databinding.DataBindingUtil
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import javax.inject.Inject

class MoviesAdapter @Inject constructor():
    PagingDataAdapter<UserItemUiState, MoviesAdapter.UserViewHolder>(Comparator)/*, ShowData*/ {

    private var onMovieClickListener: ((Int) -> Unit)? = null

    inner class UserViewHolder(
        private val binding: ItemMovieBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(userItemUiState: UserItemUiState) {
            binding.root.setOnClickListener{
                onMovieClickListener.let { click ->
                    if (click != null) {
                        click(userItemUiState.getId())
                    }
                }
            }
            binding.executeWithAction {
                this.userItemUiState = userItemUiState
            }

        }
    }
    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        getItem(position)?.let { userItemUiState -> holder.bind(userItemUiState) }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val binding = DataBindingUtil.inflate<ItemMovieBinding>(
            LayoutInflater.from(parent.context),
            R.layout.item_movie,
            parent,
            false
        )

        return UserViewHolder(binding)
    }


    fun setOnPeliculaClickListener(listener: ((Int) -> Unit)) {
        this.onMovieClickListener = listener
    }

    object Comparator : DiffUtil.ItemCallback<UserItemUiState>() {
        override fun areItemsTheSame(oldItem: UserItemUiState, newItem: UserItemUiState): Boolean {
            return oldItem.getId() == newItem.getId()
        }

        override fun areContentsTheSame(
            oldItem: UserItemUiState,
            newItem: UserItemUiState
        ): Boolean {
            return oldItem == newItem
        }
    }

}
