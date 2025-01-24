package com.screen.doorgelezen.data.repository

import com.screen.doorgelezen.data.api.AuthenticationAPI
import com.screen.doorgelezen.data.models.AuthModel
import okhttp3.Cookie
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(private val authenticationAPI: AuthenticationAPI) :AuthRepository {
    override suspend fun login(authModel: AuthModel): Resource<Unit> {
//        TODO("Not yet implemented")
        return try {
            val result = authenticationAPI.logIn(authModel)
            if(result.isSuccess) Resource.Success(Unit)
            else Resource.Failure()
        }catch (e:Exception){
            e.printStackTrace()
            Resource.Failure(e)
        }
    }

    override suspend fun logout(): Resource<Unit> {
//        TODO("Not yet implemented")
        return Resource.Success(Unit)
    }
}