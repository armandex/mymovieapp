package aguinaga.armando.mymovieapp.ui.splash

import aguinaga.armando.mymovieapp.utils.App
import aguinaga.armando.mymovieapp.databinding.ActivitySplashBinding
import aguinaga.armando.mymovieapp.ui.viewmodels.PreferencesViewModel
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SplashActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySplashBinding
    private val preferencesViewModel: PreferencesViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)
        Handler(Looper.getMainLooper()).postDelayed({
            val ancho = this.resources.displayMetrics.widthPixels
            preferencesViewModel.setAnchoDispositivo(ancho)
            if (preferencesViewModel.getUserName() == "") {
                App.goLogin(applicationContext, null)
            } else {
                finish()
                App.goMain(applicationContext, null)
            }
        }, 2500)
    }
}