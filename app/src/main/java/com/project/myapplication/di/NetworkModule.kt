package com.project.myapplication.di

import android.content.Context
import com.google.gson.GsonBuilder
import com.project.myapplication.R
import com.project.myapplication.data.api.UnsplashApi
import com.project.myapplication.util.schedulers.ApplicationSchedulerProvider
import com.project.myapplication.util.schedulers.SchedulerProvider
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.io.IOException
import java.util.concurrent.TimeUnit

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    @Provides
    fun provideOkHttpClient(@ApplicationContext context: Context): OkHttpClient {
        val builder =  OkHttpClient.Builder()
            .connectTimeout(120, TimeUnit.SECONDS)
            .readTimeout(120, TimeUnit.SECONDS)
        builder.addInterceptor(HttpLoggingInterceptor()
            .setLevel(HttpLoggingInterceptor.Level.BODY))
        builder.addInterceptor(Interceptor { chain ->
            val request: Request =
                chain.request().newBuilder().addHeader("Authorization",
                    "Client-ID ${context.getString(R.string.access_key)}").build()
            chain.proceed(request)
        })
        return builder.build()
    }

    @Provides
    fun provideUnsplashServiceApi(@ApplicationContext context: Context, client: OkHttpClient): UnsplashApi {
        val retrofit = Retrofit.Builder()
            .baseUrl(context.getString(R.string.base_url))
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create(
                GsonBuilder().setLenient().create()))
            .client(client)
            .build()
        return retrofit.create(UnsplashApi::class.java)
    }

    @Provides
    fun provideSchedulerProvider(): SchedulerProvider {
        return ApplicationSchedulerProvider()
    }
}