package aguinaga.armando.mymovieapp.ui.movies

import aguinaga.armando.mymovieapp.databinding.ActivityDrawerBinding
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class MoviesActivity: AppCompatActivity() {

    private lateinit var binding: ActivityDrawerBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDrawerBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }


}