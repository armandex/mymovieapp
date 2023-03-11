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

    fun setUserName(value: String) {
        misprefsconfiguracion.edit { putString(Constantes.username, value) }
    }
    fun getUserName(): String {
        return misprefsconfiguracion.getString(Constantes.username, "")!!
    }
    fun setAnchoDispositivo(value: Int) {
        misprefsconfiguracion.edit { putInt("anchoDispositivo", value) }
    }
    fun getAnchoDispositivo(): Int {
        return misprefsconfiguracion.getInt("anchoDispositivo", 0)
    }
    fun setAltoDispositivo(value: Float) {
        misprefsconfiguracion.edit { putFloat("altoDispositivo", value) }
    }
    fun getAltoDispositivo(): Float {
        return misprefsconfiguracion.getFloat("altoDispositivo", 0f)!!
    }
    
}


