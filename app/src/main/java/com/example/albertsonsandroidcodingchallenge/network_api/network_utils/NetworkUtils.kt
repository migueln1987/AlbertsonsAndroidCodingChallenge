package com.example.albertsonsandroidcodingchallenge.network_api.network_utils

import android.content.Context
import android.net.ConnectivityManager
import android.widget.Toast
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader
import java.util.concurrent.TimeUnit

object NetworkUtils {

    val TIMEOUT = 60 * 1000

    val HTTP_SUCCESS = 200
    val HTTP_404 = 404
    val HTTP_500 = 500
    val HTTP_RETROFIT_FAILURE = 0

    fun isConnectedToInternet(context: Context): Boolean {
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetworkInfo = connectivityManager.activeNetworkInfo
        return if (activeNetworkInfo != null && activeNetworkInfo.isConnected)
            true
        else {
            Toast.makeText(context, "Please check you internet connection", Toast.LENGTH_LONG)
                .show()
            false
        }
    }


    @JvmStatic
    fun buildRetrofit(): Retrofit {
        val okHttpClient = OkHttpClient()

        val gson = GsonBuilder()
            .setLenient()
            .create()
        return Retrofit.Builder()
            .baseUrl(NetworkURLS.SERVER_URL)
            .client(
                okHttpClient.newBuilder().connectTimeout(TIMEOUT.toLong(), TimeUnit.SECONDS)
                    .readTimeout(TIMEOUT.toLong(), TimeUnit.SECONDS)
                    .writeTimeout(TIMEOUT.toLong(), TimeUnit.SECONDS).build()
            )
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()

    }

    @JvmStatic
    fun getStringResponseFromRaw(response: ResponseBody): String {
        var reader: BufferedReader? = null
        val sb = StringBuilder()
        try {

            reader = BufferedReader(InputStreamReader(response.byteStream()))

            var line: String = reader.readLine()

            try {
                while (line != null) {
                    sb.append(line)
                    line = reader.readLine()
                }
            } catch (e: IOException) {
                e.printStackTrace()
            }

        } catch (e: Exception) {
            e.printStackTrace()
        }

        return sb.toString()
    }

    fun getStringResponseFromRaw(response: Response<ResponseBody>): String {
        var reader: BufferedReader? = null
        val sb = StringBuilder()
        try {

            reader = BufferedReader(InputStreamReader(response.body()!!.byteStream()))

            var line: String = reader.readLine()

            try {
                while (line != null) {
                    sb.append(line)
                    line = reader.readLine()
                }
            } catch (e: IOException) {
                e.printStackTrace()
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }

        return sb.toString()
    }

}
