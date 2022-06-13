package com.bangkit.capsstonebangkit.di

import com.bangkit.capsstonebangkit.data.api.ApiHelper
import com.bangkit.capsstonebangkit.data.api.ApiService
import com.bangkit.capsstonebangkit.data.api.setCookieStore
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

private const val BASE_URL = "https://rehatapi-6aa42ekb5a-et.a.run.app/"
val networkModule = module {
    single {
        HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
    }

    single {

        OkHttpClient.Builder()
            .addInterceptor(get<HttpLoggingInterceptor>())
            .setCookieStore(get())
//            .cookieJar(JavaNetCookieJar(CookieManager()))
            .build()
    }
    single {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(get())
            .build()
    }
    single {
        get<Retrofit>().create(ApiService::class.java)
    }

    singleOf(::ApiHelper)
}
