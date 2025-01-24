package com.screen.doorgelezen.data.repository

import com.screen.doorgelezen.data.models.AuthModel
import okhttp3.Cookie

interface AuthRepository {

    suspend fun login(authModel: AuthModel):Resource<Unit>

    suspend fun logout():Resource<Unit>

}