package com.example.web_sdk

import ApiClient
import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import okhttp3.WebSocket
import okhttp3.WebSocketListener
import okhttp3.internal.toHexString
import okio.ByteString
import java.io.ByteArrayOutputStream

class CameraCaptureActivity : AppCompatActivity() {
    private val CAMERA_REQUEST_CODE = 100
    private val NORMAL_CLOSURE_STATUS = 1000

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Inicie a câmera
        val cameraIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        startActivityForResult(cameraIntent, CAMERA_REQUEST_CODE)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == CAMERA_REQUEST_CODE && resultCode == RESULT_OK) {
            // Obtenha a imagem, converta-a em bytes e envie-a
            val image = data?.extras?.get("data") as Bitmap
            val outputStream = ByteArrayOutputStream()
            image.compress(Bitmap.CompressFormat.JPEG, 100, outputStream)
            val byteArray = outputStream.toByteArray()

            // Crie um WebSocket e envie a imagem
            val webSocket = OkHttpClient().newWebSocket(
                Request.Builder().url("http:192.168.15.29:8080/websocket").build(),
                object : WebSocketListener() {
                    override fun onOpen(webSocket: WebSocket, response: Response) {
                        webSocket.send(ByteString.of(*byteArray))
                        webSocket.close(NORMAL_CLOSURE_STATUS, "Image sent")
                    }
                }
            )
        }
    }
}
