package aguinaga.armando.mymovieapp.ui.viewmodels

import aguinaga.armando.mymovieapp.utils.Constantes
import android.content.SharedPreferences
import androidx.core.content.edit
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import javax.inject.Named

@HiltViewModel
class PreferencesViewModel @Inject constructor(
    @Named("default")
    private val misprefsconfiguracion: SharedPreferences,
    @Named("security")
    private val Misprefsseguridad: SharedPreferences
    ) : ViewModel() {

    var userName: String
    get() = misprefsconfiguracion.getString(Constantes.username, "")!!
    set (value: String) {
        misprefsconfiguracion.edit { putString(Constantes.username, value) }
    }
   var widthDevice : Int
   get() = misprefsconfiguracion.getInt("anchoDispositivo", 0)
    set(value) {
        misprefsconfiguracion.edit { putInt("anchoDispositivo", value) }
    }
    fun getAltoDispositivo(): Float {
        return misprefsconfiguracion.getFloat("altoDispositivo", 0f)!!
    }
}


