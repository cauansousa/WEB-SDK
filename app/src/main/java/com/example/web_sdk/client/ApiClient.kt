import com.example.web_sdk.model.RespostaServidor
import com.example.web_sdk.service.ApiService
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.FileOutputStream
import java.io.IOException

class ApiClient {

    private val BASE_URL = "http://192.168.15.29:8080/imagereciver"

    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private val apiService = retrofit.create(ApiService::class.java)

    fun enviarImagem(imageData: ByteArray) {
        val imageRequestBody = imageData.toRequestBody("image/*".toMediaTypeOrNull())
        val imagePart = MultipartBody.Part.createFormData("imagem", "imagem_tempo_real.jpg", imageRequestBody)

        val call = apiService.enviarImagem(imagePart)

        call.enqueue(object : Callback<RespostaServidor> {
            override fun onResponse(call: Call<RespostaServidor>, response: Response<RespostaServidor>) {
                if (response.isSuccessful) {
                    val respostaServidor = response.body()
                    respostaServidor?.let {
                        // Trate a resposta do servidor aqui
                        println("Resposta do servidor: ${it.mensagem}")
                    }
                } else {
                    println("Erro na chamada: ${response.code()}")
                }
            }

            override fun onFailure(call: Call<RespostaServidor>, t: Throwable) {
                println("Erro na chamada: ${t.message}")
            }
        })
    }
}
