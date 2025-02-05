package com.screen.doorgelezen.data.repository

import com.screen.doorgelezen.data.models.AuthModel

interface AuthRepository {

    suspend fun login(authModel: AuthModel):Resource<Unit>

    suspend fun logout():Resource<Unit>

}