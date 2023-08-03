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

        remoteMessage.data.isNotEmpty().let {
            Log.d(TAG, "Message data payload: " + remoteMessage.data)

            if (remoteMessage.data.containsKey("speak")){
                val text = remoteMessage.data["speak"]
                temiCaller.speakCaller(text!!)

            } else if (remoteMessage.data.containsKey("goto")) {
                val locations = remoteMessage.data["goto"]
                temiCaller.gotoCaller(locations!!)
            }else if (remoteMessage.data.containsKey("follow")) {
                if (remoteMessage.data["follow"].equals("true"))
                    temiCaller.followCaller() else temiCaller.unfollowCaller()
            } else {
                Log.d(TAG, "Nothing here!")
            }
        }
    }

    companion object {
        private const val TAG = "FCM Service"
    }
}