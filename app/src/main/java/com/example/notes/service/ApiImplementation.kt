package com.example.notes.service

import ApiService
import android.content.Context
import android.widget.Toast
import com.example.notes.R
import com.example.notes.R.string.error_message
import retrofit.GsonConverterFactory
import retrofit.Retrofit
import retrofit.Call
import retrofit.Callback
import retrofit.Response
import javax.inject.Inject

class ApiImplementation @Inject constructor() {
    fun getUVIndex(
        context: Context,
        onSuccess: (UVResponse) -> Unit,
        onFail: () -> Unit,
        loadingFinished: () -> Unit
    ) {
        val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl(context.getString(R.string.api_url))
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val service: ApiService = retrofit.create(ApiService::class.java)

        val call: Call<UVResponse> = service.getUVIndex(-34, -59, "", context.getString(R.string.access_token),)

        call.enqueue(object : Callback<UVResponse> {
            override fun onResponse(response: Response<UVResponse>?, retrofit: Retrofit?) {
                loadingFinished()
                if (response != null) {
                    if (response.isSuccess) {
                        response.body()?.let { uvResponse ->
                            onSuccess(uvResponse)
                        } ?: onFail()
                    } else {
                        Toast.makeText(context, context.getString(error_message), Toast.LENGTH_SHORT).show()
                        onFail()
                    }
                }
            }

            override fun onFailure(t: Throwable?) {
                Toast.makeText(context, context.getString(error_message), Toast.LENGTH_SHORT).show()
                onFail()
                loadingFinished()
            }
        })
    }
}