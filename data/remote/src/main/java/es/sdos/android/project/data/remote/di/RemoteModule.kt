package es.sdos.android.project.data.remote.di

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import es.sdos.android.project.data.datasource.games.GamesRemoteDataSource
import es.sdos.android.project.data.remote.games.GamesRemoteDataSourceImpl
import es.sdos.android.project.data.remote.games.GamesWs
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class RemoteModule {
    companion object {
        private const val BASE_URL = "https://api.myjson.com/"
    }

    @Provides
    fun interceptorProvider(): Interceptor =
        HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.HEADERS
        }

    @Provides
    fun okHttpClientProvider(interceptor: Interceptor) =
        OkHttpClient.Builder()
            .addInterceptor(interceptor)
            .build()

    @Provides
    fun gsonProvider() =
        GsonBuilder()
            .setDateFormat("dd/MM/yyyy")
            .create()

    @Provides
    @Singleton
    fun retrofitProvider(okHttpClient: OkHttpClient, gson: Gson) =
        Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()

    @Provides
    fun gamesServiceProvider(retrofit: Retrofit) =
        retrofit.create(GamesWs::class.java)

    @Provides
    fun gamesRemoteDataSourceProvider(gamesService: GamesWs) =
        GamesRemoteDataSourceImpl(gamesService) as GamesRemoteDataSource

}