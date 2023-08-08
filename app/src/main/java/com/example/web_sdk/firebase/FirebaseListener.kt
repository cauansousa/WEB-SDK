package com.example.web_sdk.firebase

import android.util.Log
import com.example.web_sdk.caller.TemiCaller
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage


class FirebaseListener : FirebaseMessagingService() {

    private val temiCaller: TemiCaller = TemiCaller()

    override fun onMessageReceived(remoteMessage: RemoteMessage) {
        super.onMessageReceived(remoteMessage)

        Log.d(TAG, "From: ${remoteMessage.from}")

        if (remoteMessage.data.isNotEmpty()) {
            Log.d("TEMI", remoteMessage.data.toString())
            remoteMessage.data.keys.forEach { key ->
                when(key){
                    "goto" -> remoteMessage.data["goto"]?.let { if (!temiCaller.gotoCaller(it)){
                        println("Go To failed!")} }
                    "follow" -> remoteMessage.data["follow"]?.let { temiCaller.followCaller() }
                    "unfollow" -> remoteMessage.data["unfollow"]?.let { temiCaller.unfollowCaller() }
                    "speak" -> remoteMessage.data["speak"]?.let { if (!temiCaller.speakCaller(it)) {
                        println("Speak failed!")} }
                }
            }
        }
    }

    companion object {
        private const val TAG = "FCM Service"
    }
}