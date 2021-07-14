package com.gabez.nearesttoiletpl.receivers

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import com.gabez.nearesttoiletpl.NetworkUtil
import com.gabez.nearesttoiletpl.ui.CurrentActivityUtil
import com.gabez.nearesttoiletpl.ui.StartActivity

class NetworkStatusChangedReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {
        val status: Int = NetworkUtil.getConnectivityStatusString(context)
        if ("android.net.conn.CONNECTIVITY_CHANGE" == intent.action) {
            if (status == NetworkUtil.NETWORK_STATUS_NOT_CONNECTED) {
                if (CurrentActivityUtil.currentActivityClassName == StartActivity.javaClass.name)
                    StartActivity.showNoInternetDialog()
            } else {
                if (CurrentActivityUtil.currentActivityClassName == StartActivity.javaClass.name)
                    StartActivity.hideNoInternetDialog()
            }
        }
    }
}