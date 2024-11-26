package com.example.coinbase.di

import android.content.Context
import com.example.coinbase.BuildConfig
import com.example.coinbase.common.connectivity.ConnectivityManagerHelper
import com.example.coinbase.data.network.intercptor.NetworkStatusInterceptor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    private const val TIMEOUT_SECONDS = 15L

    @Provides
    fun provideConnectivityManagerHelper(
        @ApplicationContext context: Context
    ): ConnectivityManagerHelper {
        return ConnectivityManagerHelper(context)
    }

    @Provides
    fun provideLoggingInterceptor(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor().apply {
            level = if (BuildConfig.DEBUG) {
                HttpLoggingInterceptor.Level.BODY
            } else {
                HttpLoggingInterceptor.Level.NONE
            }
        }
    }

    @Provides
    fun provideOkHttpClient(
        interceptors: @JvmSuppressWildcards(true) List<Interceptor>
    ): OkHttpClient {
        val builder = OkHttpClient.Builder()
            .readTimeout(TIMEOUT_SECONDS, TimeUnit.SECONDS)
            .connectTimeout(TIMEOUT_SECONDS, TimeUnit.SECONDS)

        interceptors.forEach { builder.addInterceptor(it) }

        return builder.build()
    }

    @Provides
    fun provideGsonConverterFactory(): GsonConverterFactory {
        return GsonConverterFactory.create()
    }

    @Provides
    fun provideInterceptors(
        loggingInterceptor: HttpLoggingInterceptor,
        networkStatusInterceptor: NetworkStatusInterceptor
    ): List<Interceptor> {
        return listOf(
            loggingInterceptor,
            networkStatusInterceptor
        )
    }

    @Provides
    fun provideNetworkStatusInterceptor(
        connectivityManagerHelper: ConnectivityManagerHelper
    ): NetworkStatusInterceptor {
        return NetworkStatusInterceptor(connectivityManagerHelper)
    }

    @Provides
    fun provideRetrofit(
        okHttpClient: OkHttpClient,
        converterFactory: GsonConverterFactory,
    ): Retrofit.Builder {
        return Retrofit.Builder()
            .baseUrl("https://api.coinbase.com/v2/")
            .client(okHttpClient)
            .addConverterFactory(converterFactory)
    }
}