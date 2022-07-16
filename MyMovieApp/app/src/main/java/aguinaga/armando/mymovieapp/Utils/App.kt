package aguinaga.armando.mymovieapp.Utils

import aguinaga.armando.mymovieapp.ui.login.LoginActivity
import aguinaga.armando.mymovieapp.ui.movies.MoviesActivity
import android.content.Context
import android.content.Intent
import android.content.Intent.FLAG_ACTIVITY_NEW_TASK
import android.os.Bundle

class App {
    companion object {
        fun goMain(context: Context, bundle: Bundle?) {
            comun(context, bundle, MoviesActivity::class.java, FLAG_ACTIVITY_NEW_TASK)
        }

        fun goLogin(context: Context, bundle: Bundle?) {
            comun(context, bundle, LoginActivity::class.java, FLAG_ACTIVITY_NEW_TASK)
        }

        /*fun goRegister(context: Context, bundle: Bundle?) {
            comun(context, bundle, RegisterActivity::class.java, FLAG_ACTIVITY_NEW_TASK)
        }*/

        private fun comun(context: Context, bundle: Bundle?, clase: Class<*>, flags: Int) {
            val intent = Intent(context, clase)
            if (bundle != null)
                intent.putExtras(bundle)
            intent.addFlags(flags)
            context.startActivity(intent)
        }
    }
}