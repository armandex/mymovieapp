package aguinaga.armando.mymovieapp.ui.movies

import aguinaga.armando.mymovieapp.R
import aguinaga.armando.mymovieapp.databinding.ActivityDrawerBinding
import aguinaga.armando.mymovieapp.databinding.DrawerHeaderBinding
import aguinaga.armando.mymovieapp.ui.viewmodels.PreferencesViewModel
import aguinaga.armando.mymovieapp.utils.App
import android.os.Bundle
import android.view.MenuItem
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.viewModels
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.app_bar_main.view.*
import timber.log.Timber

@AndroidEntryPoint
class MoviesActivity: AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    private lateinit var binding: ActivityDrawerBinding
    private lateinit var drawerHeaderBinding: DrawerHeaderBinding
    private val preferencesViewModel: PreferencesViewModel by viewModels()
    private lateinit var txtName: TextView
    private lateinit var imageDrawer: ImageView
    private lateinit var titleToolbar: Toolbar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDrawerBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initialize()
        setToolbarToMainActivity()
        if (savedInstanceState == null) {
            //binding.appBarMain.toolbar.drawer_title.text = "My Movie App"
            App.goMovies(this, null)

        }
        Timber.e("onCreate")

    }

    fun setToolbarToMainActivity() {
        //titleToolbar = findViewById(R.id.toolbar)
        setSupportActionBar(binding.appBarMain.toolbar)
        //binding.appBarMain.toolbar.drawer_title
        //supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        //supportActionBar!!.setDisplayShowHomeEnabled(true)
    }
    private fun initialize(){
        val drawer: DrawerLayout = binding.drawer
        val toogle = ActionBarDrawerToggle(
            this,
            drawer,
            binding.appBarMain.toolbar,
            R.string.navigation_drawer_open,
            R.string.navigation_drawer_close
        )
        drawer.setDrawerListener(toogle)
        toogle.syncState()
        binding.navigationView.setNavigationItemSelectedListener(this)
        val headerBinding = binding.navigationView.getHeaderView(0)
        drawerHeaderBinding = DrawerHeaderBinding.bind(headerBinding)
        txtName = drawerHeaderBinding.txtName
        imageDrawer = drawerHeaderBinding.imageHeader
        txtName.text = preferencesViewModel.getUserName()


    }
    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        binding.drawer.closeDrawer(GravityCompat.START)
        when (item.itemId) {
            R.id.navItemCartelera -> {
                App.goMovies(this,null)
                //drawer_title.text = "My Movie App"
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