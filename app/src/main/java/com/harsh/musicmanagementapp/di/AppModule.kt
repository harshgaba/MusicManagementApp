package com.harsh.musicmanagementapp.di

import android.content.Context
import androidx.room.Room
import com.harsh.musicmanagementapp.data.local.TopAlbumDatabase
import com.harsh.musicmanagementapp.data.remote.MusicApi
import com.harsh.musicmanagementapp.data.repository.MusicRepositoryImpl
import com.harsh.musicmanagementapp.domain.repository.MusicRepository
import com.harsh.musicmanagementapp.shared.Constants
import com.harsh.musicmanagementapp.shared.Constants.DATABASE_NAME
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor


@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideTopAlbumDatabase(
        @ApplicationContext context: Context
    ) = Room.databaseBuilder(context, TopAlbumDatabase::class.java, DATABASE_NAME).build()

    @Singleton
    @Provides
    fun provideTopAlbumDao(
        database: TopAlbumDatabase
    ) = database.topAlbumDao()

    @Provides
    @Singleton
    fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor {

        return HttpLoggingInterceptor().apply {
            setLevel(HttpLoggingInterceptor.Level.BODY)
        }
    }

    @Provides
    @Singleton
    fun provideOkhttpClient(logger: HttpLoggingInterceptor): OkHttpClient {
        val client = OkHttpClient.Builder()
        client.addInterceptor(logger)
        return client.build()
    }

    @Provides
    @Singleton
    fun provideFleetApi(okHttpClient: OkHttpClient): MusicApi {
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
            .create(MusicApi::class.java)
    }

    @Provides
    @Singleton
    fun provideFleetRepository(api: MusicApi): MusicRepository {
        return MusicRepositoryImpl(api)
    }
}