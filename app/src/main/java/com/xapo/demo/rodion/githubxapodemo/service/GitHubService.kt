package com.xapo.demo.rodion.githubxapodemo.service

import com.xapo.demo.rodion.githubxapodemo.model.Hub
import io.reactivex.Observable
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

/**
 * GitHub API service.
 */
interface GitHubService {

    @GET("repositories")
    fun getPublicHubs(): Observable<List<Hub>>

    /**
     * Companion object to create the GithubService
     */
    companion object Factory {
        fun create(): GitHubService {
            val retrofit = Retrofit.Builder()
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .baseUrl("https://api.github.com/")
                    .build()

            return retrofit.create(GitHubService::class.java)
        }
    }

}