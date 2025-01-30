package com.screen.doorgelezen.data.repository

import com.screen.doorgelezen.data.CookieJar
import com.screen.doorgelezen.data.api.AuthenticationAPI
import com.screen.doorgelezen.data.models.AuthModel
import com.screen.doorgelezen.utils.printDebug
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(private val authenticationAPI: AuthenticationAPI, private val cookieJar: CookieJar) :AuthRepository {
    override suspend fun login(authModel: AuthModel): Resource<Unit> {
        return try {
            val result = authenticationAPI.logIn(authModel)
            printDebug("repo - ${result}")
            if(result.isSuccess) Resource.Success(Unit)
            else Resource.Failure()
        }catch (e:Exception){
            e.printStackTrace()
            Resource.Failure(e)
        }
    }

    override suspend fun logout(): Resource<Unit> {
        return try {
            val result = authenticationAPI.logOut()
            if(result.isFailure) return Resource.Failure(Exception("Error logging out user!"))

            cookieJar.clear()
            Resource.Success(Unit)
        }catch (e:Exception){
            e.printStackTrace()
            Resource.Failure(e)
        }

    }
}