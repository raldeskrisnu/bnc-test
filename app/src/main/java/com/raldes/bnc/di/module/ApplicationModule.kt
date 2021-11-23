package com.raldes.bnc.di.module

import android.content.Context
import androidx.lifecycle.ViewModelProvider
import androidx.room.Room
import com.raldes.bnc.constant.Constant
import com.raldes.bnc.db.MovieDatabase
import com.raldes.bnc.di.annotation.scope.DbName
import com.raldes.bnc.network.AndroidInterceptor
import com.raldes.bnc.utils.ViewModelFactory
import com.squareup.picasso.BuildConfig
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Named
import javax.inject.Singleton

@Module
class ApplicationModule {

    @Singleton
    @Provides
    fun provideGsonFactory(): GsonConverterFactory = GsonConverterFactory.create()

    @Singleton
    @Provides
    fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor {

        val httpLoggingInterceptor = HttpLoggingInterceptor()

        if (BuildConfig.DEBUG) {
            httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        } else {
            httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.NONE
        }

        return httpLoggingInterceptor
    }

    @Singleton
    @Provides
    fun provideRxJavaCallAdapter(): RxJava2CallAdapterFactory = RxJava2CallAdapterFactory.create()

    @Singleton
    @Provides
    @Named("ok-1")
    fun provideOkHttpClient( httpLoggingInterceptor: HttpLoggingInterceptor,
                             androidInterceptor: AndroidInterceptor
    ): OkHttpClient {
        return OkHttpClient.Builder()
            .connectTimeout(20, TimeUnit.SECONDS)
            .readTimeout(20, TimeUnit.SECONDS)
            .addNetworkInterceptor(httpLoggingInterceptor)
            .addInterceptor(androidInterceptor)
            .build()
    }

    @Singleton
    @Provides
    fun provideRetrofit(@Named("ok-1") okHttpClient: OkHttpClient, gsonConverterFactory: GsonConverterFactory,
                        rxJavaCallAdapterFactory: RxJava2CallAdapterFactory
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl(Constant.BASE_URL)
            .addConverterFactory(gsonConverterFactory)
            .addCallAdapterFactory(rxJavaCallAdapterFactory)
            .client(okHttpClient)
            .build()
    }

    @Singleton
    @Provides
    fun androidInterceptor(): AndroidInterceptor = AndroidInterceptor()

    @Provides
    fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory = factory

    @Provides
    @DbName
    fun provideDbName() = "studentdata.db"

    @Provides fun provideStudentDatabase(context: Context, @DbName dbName: String): MovieDatabase{
        return Room.databaseBuilder(context,
            MovieDatabase::class.java, dbName)
            .build()
    }

    @Provides
    fun provideStudentDao(movieDatabase: MovieDatabase) = movieDatabase.dataMovieDao()


}