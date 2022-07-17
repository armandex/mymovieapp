package aguinaga.armando.mymovieapp.utils


import aguinaga.armando.mymovieapp.R
import android.app.Dialog
import android.content.Context
import android.view.Window
import android.widget.TextView
import timber.log.Timber

object DialogUtils {
    private lateinit var customDialog: Dialog

    fun progressWithTitle(context: Context?, cancelable: Boolean, titulo: String?) {
        try {
            // con este tema personalizado evitamos los bordes por defecto
            customDialog = Dialog(context!!, R.style.Theme_Dialog_Translucent)
            //deshabilitamos el título por defecto
            customDialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
            //obligamos al usuario a pulsar los botones para cerrarlo
            if (cancelable) {
                customDialog.setCancelable(true)
            } else {
                customDialog.setCancelable(false)
            }
            //establecemos el contenido de nuestro dialog
            customDialog.setContentView(R.layout.dialog_progress_title)
            val txt_titulo = customDialog.findViewById<TextView>(R.id.dialog_title)
            txt_titulo.text = titulo
            customDialog.show()
        } catch (ex: Exception) {
            Timber.tag("dialogoConTitulo").e("error: %s", ex.toString())
        }
    }

    fun closeProgressWithTitle() {
        try {
            if (customDialog.isShowing) {
                customDialog.dismiss()
            }
        } catch (ex: Exception) {
            Timber.tag("cerrarDialog").e("error: %s", ex.toString())
        }
    }


}