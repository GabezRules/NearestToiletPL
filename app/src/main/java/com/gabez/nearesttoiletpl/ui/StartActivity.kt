package com.gabez.nearesttoiletpl.ui

import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.gabez.nearesttoiletpl.NetworkUtil
import com.gabez.nearesttoiletpl.R

class StartActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_start)

        startActivityContext = this
        CurrentActivityUtil.currentActivityClassName = this.javaClass.name

        if (NetworkUtil.getConnectivityStatus(this) === 0) {
            showNoInternetDialog()
        }

        val connectivityManager = this.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        connectivityManager?.let {
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
                it.registerDefaultNetworkCallback(object : ConnectivityManager.NetworkCallback() {
                    override fun onAvailable(network: Network) {
                        hideNoInternetDialog()
                    }
                    override fun onLost(network: Network) {
                        showNoInternetDialog()
                    }
                })
            }
        }
    }

    companion object{
        lateinit var startActivityContext: StartActivity
        var noInternetDialog: AlertDialog? = null

        fun showNoInternetDialog() {
            if (startActivityContext != null) {
                noInternetDialog =
                    AlertDialog.Builder(startActivityContext).create()
                noInternetDialog!!.setTitle(startActivityContext.resources.getString(R.string.service_unavailable))
                noInternetDialog!!.setMessage(startActivityContext.resources.getString(R.string.no_internet_alert))
                noInternetDialog!!.setCancelable(false)
                noInternetDialog!!.show()
            }
        }

        fun hideNoInternetDialog() {
            if (noInternetDialog != null) {
                noInternetDialog!!.hide()
                noInternetDialog = null
            }
        }
    }
}