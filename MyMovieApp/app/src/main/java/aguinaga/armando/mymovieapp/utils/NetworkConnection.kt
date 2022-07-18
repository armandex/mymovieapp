package aguinaga.armando.mymovieapp.utils

import android.annotation.TargetApi
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.net.*
import android.os.Build
import androidx.lifecycle.LiveData
import timber.log.Timber
import java.lang.Exception

class NetworkConnection(private val context: Context) : LiveData<Boolean>() {

    private var connectivityManager: ConnectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

    private lateinit var networkCallback: ConnectivityManager.NetworkCallback

    override fun onActive() {
        super.onActive()
        updateConnection()
        when {
            // for devices above Nougat
            Build.VERSION.SDK_INT >= Build.VERSION_CODES.N -> {
                try {
                    connectivityManager.registerDefaultNetworkCallback(connectivityManagerCallback())
                } catch (ex: Exception) {
                    connectivityManager.unregisterNetworkCallback(connectivityManagerCallback())
                }
            }
            // for devices b/w Lollipop and Nougat
            Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP -> {
                lollipopNetworkRequest()
            }
            //below lollipop
            else -> {
                try {
                    context.registerReceiver(
                            networkReceiver,
                            IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION)
                    )
                } catch (ex: Exception) {
                    Timber.e("onActive < N: $ex")
                }
            }


        }
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    private fun lollipopNetworkRequest() {
        try {
            val builder = NetworkRequest.Builder()
                    .addTransportType(NetworkCapabilities.TRANSPORT_CELLULAR)
                    .addTransportType(NetworkCapabilities.TRANSPORT_WIFI)
            connectivityManager.registerNetworkCallback(builder.build(), connectivityManagerCallback())
        } catch (ex: Exception){
            Timber.e("lollipopNetworkRequest: $ex")
        }
    }

    override fun onInactive() {
        super.onInactive()
        try {
            connectivityManager.unregisterNetworkCallback(networkCallback)
        } catch (e: Exception) {
            Timber.e("onInactive: $e")
        }
    }

    private fun connectivityManagerCallback(): ConnectivityManager.NetworkCallback {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            networkCallback = object : ConnectivityManager.NetworkCallback() {

                override fun onAvailable(network: Network) {
                    super.onAvailable(network)
                    postValue(true)
                }

                override fun onLost(network: Network) {
                    super.onLost(network)
                    postValue(false)
                }

            }
            return networkCallback
        } else {
            throw IllegalAccessError("Error")
        }
    }

    private val networkReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context?, intent: Intent?) {
            updateConnection()
        }
    }

    private fun updateConnection() {
        val activeNetwork: NetworkInfo? = connectivityManager.activeNetworkInfo
        if (activeNetwork?.isConnected == true) {
            postValue(true)
        } else {
            postValue(false)
        }
    }


}