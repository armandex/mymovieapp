package aguinaga.armando.mymovieapp.ui.splash

import aguinaga.armando.mymovieapp.Utils.App
import aguinaga.armando.mymovieapp.databinding.ActivitySplashBinding
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity

class SplashActivity : AppCompatActivity(){

    private lateinit var binding: ActivitySplashBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)
        Handler(Looper.getMainLooper()).postDelayed({

            if (true){
                finish()
                App.goLogin(applicationContext,null)
            }else{
                finish()
                App.goMain(applicationContext,null)
            }
        },2500)
    }
}