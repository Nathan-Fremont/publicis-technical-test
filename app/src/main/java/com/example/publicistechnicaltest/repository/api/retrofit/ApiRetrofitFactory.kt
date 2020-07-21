package com.example.publicistechnicaltest.repository.api.retrofit

import android.content.Context
import com.example.publicistechnicaltest.BuildConfig
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory

class ApiRetrofitFactory(private val context: Context) {
    fun buildApiRetrofit(): Retrofit {
        return Retrofit.Builder()
            .client(buildHttpClient())
            .baseUrl(BASE_URL)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(MoshiConverterFactory.create(buildMoshi()))
            .build()
    }

    fun buildApiService(retrofit: Retrofit): ApiService {
        return retrofit.create(ApiService::class.java)
    }

    private fun buildHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(buildHttpLoggingInterceptor())
            .addInterceptor(buildAddKeyInterceptor())
            .build()
    }

    private fun buildHttpLoggingInterceptor(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor().apply {
            level = if (BuildConfig.DEBUG)
                HttpLoggingInterceptor.Level.BODY
            else
                HttpLoggingInterceptor.Level.NONE
        }
    }

    private fun buildAddKeyInterceptor(): Interceptor {
        return object:Interceptor {
            override fun intercept(chain: Interceptor.Chain): Response {
                val original = chain.request()
                val originalUrl = original.url

                val newUrl = originalUrl.newBuilder()
                    .build()

                val newRequest = original.newBuilder()
                    .url(newUrl)
                    .build()
                return chain.proceed(newRequest)
            }

        }
    }

    private fun buildMoshi(): Moshi {
        return Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()
    }

    companion object {
        private const val BASE_URL = "http://henri-potier.xebia.fr/"
    }
}