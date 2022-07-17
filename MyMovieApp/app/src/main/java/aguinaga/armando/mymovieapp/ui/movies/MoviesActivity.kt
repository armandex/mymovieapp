package aguinaga.armando.mymovieapp.ui.movies

import aguinaga.armando.mymovieapp.R
import aguinaga.armando.mymovieapp.databinding.ActivityDrawerBinding
import aguinaga.armando.mymovieapp.ui.viewmodels.PreferencesViewModel
import aguinaga.armando.mymovieapp.utils.App
import aguinaga.armando.mymovieapp.utils.Constantes
import android.content.Context
import android.os.Bundle
import android.view.MenuItem
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.viewModels
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.edit
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import com.google.android.material.navigation.NavigationView
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_drawer.*
import timber.log.Timber

@AndroidEntryPoint
class MoviesActivity: AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    private lateinit var binding: ActivityDrawerBinding
    private val moviesViewmodel: MoviesViewmodel by viewModels()
    private val preferencesViewModel: PreferencesViewModel by viewModels()
    private lateinit var txt_name: TextView
    private lateinit var image_drawer: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDrawerBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initialize()
        if (savedInstanceState == null) {
            drawer_title.text = "My Movie App"
            App.goMovies(this, null)
            /*supportFragmentManager.commit {
                add(R.id.contenido, MoviesFragment())
                drawer_title.text = "My Movie App"
            }*/
        }
        Timber.e("onCreate")

    }
    private fun initialize(){
        val drawer: DrawerLayout = drawer
        val toogle = ActionBarDrawerToggle(
            this,
            drawer,
            toolbar,
            R.string.navigation_drawer_open,
            R.string.navigation_drawer_close
        )
        drawer.setDrawerListener(toogle)
        toogle.syncState()
        navigationView.setNavigationItemSelectedListener(this)
        val view = navigationView.getHeaderView(0)
        txt_name = view.findViewById(R.id.txt_name)
        image_drawer = view.findViewById(R.id.image_drawer)
        //txt_name.text = preferencias.getString("name","")


    }
    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        drawer.closeDrawer(GravityCompat.START)
        when (item.itemId) {
            R.id.navItemCartelera -> {
                App.goMovies(this,null)
                drawer_title.text = "My Movie App"
            }
            R.id.navItemLogout -> {
                cerrarSession()
                finish()
                App.goLogin(this, null)
            }
        }
        return true
    }
    private fun cerrarSession() {
        preferencesViewModel.setUserName("")
    }

}