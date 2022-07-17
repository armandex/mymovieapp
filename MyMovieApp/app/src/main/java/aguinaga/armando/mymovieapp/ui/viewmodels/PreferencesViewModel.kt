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

    fun setUserName(sdkhabilitado: String) {
        misprefsconfiguracion.edit { putString(Constantes.username, sdkhabilitado) }
    }
    fun getUserName(): String {
        return misprefsconfiguracion.getString(Constantes.username, "")!!
    }
}


