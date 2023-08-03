package com.example.web_sdk.firebase

import android.content.Intent
import android.util.Log
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import com.example.web_sdk.caller.TemiCaller
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage


class FirebaseListener : FirebaseMessagingService() {
    
    private val temiCaller: TemiCaller = TemiCaller()

    override fun onMessageReceived(remoteMessage: RemoteMessage) {
        super.onMessageReceived(remoteMessage)

        Log.d(TAG, "From: ${remoteMessage.from}")

        if (remoteMessage.data.isNotEmpty()) {
            remoteMessage.data.keys.forEach { key ->


            }
        }
    }

    companion object {
        private const val TAG = "FCM Service"
    }
}