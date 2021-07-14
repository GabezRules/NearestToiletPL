package com.gabez.nearesttoiletpl.receivers

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import com.gabez.nearesttoiletpl.NetworkUtil

class NetworkStatusChangedReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {
        val status: Int = NetworkUtil.getConnectivityStatusString(context)
        if ("android.net.conn.CONNECTIVITY_CHANGE" == intent.action) {
            if (status == NetworkUtil.NETWORK_STATUS_NOT_CONNECTED) {
                //no internet
            } else {
                //we have internet!
            }
        }
    }
}