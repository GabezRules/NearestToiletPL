package com.gabez.nearesttoiletpl.ui.fragments

import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.gabez.nearesttoiletpl.NetworkUtil
import com.gabez.nearesttoiletpl.R
import com.gabez.nearesttoiletpl.ui.CurrentActivityUtil
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mainActivityContext = this
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
        lateinit var mainActivityContext: MainActivity
        var noInternetDialog: AlertDialog? = null

        fun showNoInternetDialog() {
            noInternetDialog =
                AlertDialog.Builder(mainActivityContext).create()
            noInternetDialog!!.setTitle(mainActivityContext.resources.getString(R.string.service_unavailable))
            noInternetDialog!!.setMessage(mainActivityContext.resources.getString(R.string.no_internet_alert))
            noInternetDialog!!.setCancelable(false)
            noInternetDialog!!.show()
        }

        fun hideNoInternetDialog() {
            if (noInternetDialog != null) {
                noInternetDialog!!.hide()
                noInternetDialog = null
            }
        }
    }
}