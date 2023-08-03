package com.example.web_sdk

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.webkit.WebView
import com.example.web_sdk.databinding.ActivityMainBinding
import com.google.firebase.FirebaseApp

class MainActivity : AppCompatActivity() {

    private lateinit var myWebView: WebView

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        FirebaseApp.initializeApp(this)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        myWebView = findViewById(R.id.my_webview)
        updateUrl("http:192.168.15.29:8080/goto?token=dtoImyl5TJCISk6_FLKcDr:APA91bGD2ib5BZLdmK3zMeuaEyz3NtPdvm8J4PMoHa0zz3TMsOqiGllNe1_IKr4VpGQbaiDUyWRlkIbWJly_2uZ89FUvEgY-_x0ylsfn2QvasNeMvBTUbv1EBzmGHo74rgudOpA9DtxF")
    }
    private fun updateUrl(url: String) {
        runOnUiThread {
            myWebView.loadUrl(url)
        }
    }
}