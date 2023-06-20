package com.begobang.data.di

import androidx.room.PrimaryKey
import com.begobang.data.apiService.CharactersApiService
import com.begobang.data.remoteDataSource.CharactersRemoteDataSource
import com.begobang.data.repositoryImpl.CharactersRepositoryImpl
import com.begobang.domain.CharactersRepository
import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Qualifier
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DataModule {

    //Retrofit

    @Provides
    @ApiEndPoint
    fun provideApiEndPoint(): String = "http://gateway.marvel.com/v1/public"

    @Provides
    @PublicKey
    fun providePublicKey(): String = "1adba439e3edd5d90822343ff4ae6f76"

    @Provides
    @PrivateKey
    fun providePrivateKey(): String = "ae98c627c6443d35610a6ed5772b1d5cc9983967"

    @Provides
    @Singleton
    fun provideCharactersService(retrofit: Retrofit): CharactersApiService = createRetrofitImplementation(retrofit)

    @Provides
    @Singleton
    fun provideCharactersRemoteDataSource(service: CharactersApiService) = CharactersRemoteDataSource(service)

    @Provides
    @Singleton
    fun provideCharactersRepository(remoteDataSource: CharactersRemoteDataSource): CharactersRepository {
        return CharactersRepositoryImpl(remoteDataSource)
    }

    @Provides
    fun provideLoggingInterceptor(): HttpLoggingInterceptor = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }

    @Provides
    fun provideOkHttpClient(
        loggingInterceptor: HttpLoggingInterceptor,
        queryInterceptor: QueryInterceptor
    ): OkHttpClient = OkHttpClient.Builder()
        .addInterceptor(loggingInterceptor)
        .addInterceptor(queryInterceptor)
        .build()

    @Provides
    fun provideRestAdapter(@ApiEndPoint apiEndPoint: String, okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(apiEndPoint)
            .client(okHttpClient)
            .addConverterFactory(createConverterFactory())
            .build()
    }

    private fun createConverterFactory(vararg jsonAdapters: com.squareup.moshi.JsonAdapter<Any>): Converter.Factory {
        return MoshiConverterFactory.create(
            Moshi.Builder().run {
                jsonAdapters.forEach { add(it) }
                build()
            }
        )
    }


    private inline fun <reified T> createRetrofitImplementation(retrofit: Retrofit): T {
        return retrofit.create(T::class.java)
    }
}

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class ApiEndPoint

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class PublicKey

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class PrivateKey