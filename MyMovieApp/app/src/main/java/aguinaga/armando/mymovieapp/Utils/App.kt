package aguinaga.armando.mymovieapp.utils

import aguinaga.armando.mymovieapp.R
import aguinaga.armando.mymovieapp.ui.login.LoginActivity
import aguinaga.armando.mymovieapp.ui.moviedetail.MovieDetailFragment
import aguinaga.armando.mymovieapp.ui.movies.MoviesActivity
import aguinaga.armando.mymovieapp.ui.movies.MoviesFragment
import android.content.Context
import android.content.Intent
import android.content.Intent.FLAG_ACTIVITY_NEW_TASK
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity

object App {
        fun goMain(context: Context, bundle: Bundle?) {
            comun(context, bundle, MoviesActivity::class.java, FLAG_ACTIVITY_NEW_TASK)
        }

        fun goLogin(context: Context, bundle: Bundle?) {
            comun(context, bundle, LoginActivity::class.java, FLAG_ACTIVITY_NEW_TASK)
        }
        fun goMovies(activity: FragmentActivity, bundle: Bundle?){
            generalFragment(activity,bundle, MoviesFragment(), Constantes.moviesFragment)
        }
        fun goMovieDetail(activity: FragmentActivity, bundle: Bundle?){
            generalFragment(activity,bundle, MovieDetailFragment(), Constantes.moviesDetailFragment)
        }
        private fun comun(context: Context, bundle: Bundle?, clase: Class<*>, flags: Int) {
            val intent = Intent(context, clase)
            if (bundle != null)
                intent.putExtras(bundle)
            intent.addFlags(flags)
            context.startActivity(intent)
        }
        private fun generalFragment(activity: FragmentActivity, bundle: Bundle?, fragment: Fragment, tag: String) {
            val fragmentTransaction = activity.supportFragmentManager.beginTransaction()
                .setCustomAnimations(
                    R.anim.enter_right_to_left,
                    R.anim.exit_right_to_left,
                    R.anim.enter_left_to_right,
                    R.anim.exit_left_to_right
                )
            if (bundle != null)
                fragment.arguments = bundle
            if (tag == Constantes.moviesFragment){
                fragmentTransaction.add(R.id.nav_host_fragment_content_main, fragment)
            } else{
                fragmentTransaction.replace(R.id.nav_host_fragment_content_main, fragment)
            }
            fragmentTransaction.commit()
        }
}