package aguinaga.armando.mymovieapp.ui.movies

import aguinaga.armando.mymovieapp.R
import aguinaga.armando.mymovieapp.databinding.ActivityDrawerBinding
import aguinaga.armando.mymovieapp.databinding.DrawerHeaderBinding
import aguinaga.armando.mymovieapp.ui.viewmodels.PreferencesViewModel
import aguinaga.armando.mymovieapp.utils.App
import aguinaga.armando.mymovieapp.utils.NetworkConnection
import android.content.Context
import android.graphics.Color
import android.graphics.PorterDuff
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.viewModels
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.*
import com.google.android.material.navigation.NavigationView
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.app_bar_main.view.*
import timber.log.Timber

@AndroidEntryPoint
class MoviesActivity: AppCompatActivity(), DrawerLocker,
    NavigationView.OnNavigationItemSelectedListener {

    private lateinit var binding: ActivityDrawerBinding
    private lateinit var drawerHeaderBinding: DrawerHeaderBinding
    private val preferencesViewModel: PreferencesViewModel by viewModels()
    private lateinit var txtName: TextView
    private lateinit var imageDrawer: ImageView
    private lateinit var toolbar: Toolbar
    private lateinit var drawer: DrawerLayout
    private lateinit var navView: NavigationView
    private lateinit var appBarConfiguration: AppBarConfiguration

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDrawerBinding.inflate(layoutInflater)
        anchoDispositivo = preferencesViewModel.getAnchoDispositivo()
        setContentView(binding.root)
        setToolbarToMainActivity()
        initialize()
        verificarInternet(this)
        Timber.e("onCreate")

    }

    private fun setToolbarToMainActivity() {
        setSupportActionBar(binding.appBarMain.toolbar)
    }
    private fun initialize(){
        drawer = binding.drawerLayout
        navView = binding.navView
        toolbar = binding.appBarMain.toolbar
        val navController = findNavController(R.id.nav_host_fragment_content_main)
        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navMovies, R.id.navItemLogout
            ), drawer
        )
        val toogle = ActionBarDrawerToggle(
            this,
            drawer,
            toolbar,
            R.string.navigation_drawer_open,
            R.string.navigation_drawer_close
        )
        drawer.setDrawerListener(toogle)
        toogle.syncState()
        navView.setNavigationItemSelectedListener(this)
        val headerBinding = navView.getHeaderView(0)
        drawerHeaderBinding = DrawerHeaderBinding.bind(headerBinding)
        txtName = drawerHeaderBinding.txtName
        imageDrawer = drawerHeaderBinding.imageHeader
        txtName.text = preferencesViewModel.getUserName()

        NavigationUI.setupWithNavController(navView, navController)
        NavigationUI.setupActionBarWithNavController(this, navController, drawer)
    }
    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        drawer.closeDrawer(GravityCompat.START)
        when (item.itemId) {
            R.id.navMovies -> {

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
    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_content_main)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }

    override fun setDrawerLocked() {
        drawer.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
    }

    override fun setDrawerUnLocked() {
        drawer.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED);
    }

    override fun showArrow(value: Boolean) {
        if (value) {
            val upArrow: Drawable = this.getDrawable(androidx.transition.R.drawable.abc_ic_ab_back_material)!!;
            val toolbar = findViewById<Toolbar>(R.id.toolbar)
            setSupportActionBar(toolbar)
            toolbar.navigationIcon = upArrow
            toolbar.navigationIcon?.setColorFilter(
                Color.parseColor("#FFFFFF"),
                PorterDuff.Mode.SRC_ATOP
            )
            supportActionBar?.setHomeAsUpIndicator(upArrow)
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
        } else {
            //binding.appBarMain.toolbar.navigationIcon = null
            setSupportActionBar(toolbar)
            supportActionBar?.setDisplayHomeAsUpEnabled(false)
        }
    }

    fun verificarInternet(context: Context?) {
        val textviewOffline = findViewById<TextView>(R.id.txtoffline)
        val networkConnection = NetworkConnection(context!!)
        networkConnection.observe(this) { isConnected: Boolean ->
            if (isConnected) {
                textviewOffline.visibility = View.GONE
                isInternetEnabled = isConnected
            } else {
                textviewOffline.visibility = View.VISIBLE
                isInternetEnabled = isConnected
            }
        }
    }
    companion object {
        var isInternetEnabled = false
        var anchoDispositivo = 0
    }
}
internal interface DrawerLocker {
    fun setDrawerLocked()
    fun setDrawerUnLocked()
    fun showArrow(value: Boolean)
}