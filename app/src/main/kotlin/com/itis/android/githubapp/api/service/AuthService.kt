package com.itis.android.githubapp.api.service

import com.google.gson.JsonObject
import com.itis.android.githubapp.model.Authorization
import kotlinx.coroutines.Deferred
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.POST

interface AuthService {

    @POST("authorizations")
    fun authorizeAsync(@Header("Authorization") authorization: String,
                       @Body params: JsonObject): Deferred<Authorization>
}
