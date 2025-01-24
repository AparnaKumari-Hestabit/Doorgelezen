package com.screen.doorgelezen.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.screen.doorgelezen.data.models.AuthModel
import com.screen.doorgelezen.data.repository.AuthRepository
import com.screen.doorgelezen.data.repository.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(private val authRepository: AuthRepository): ViewModel() {

    private val _loginFlow:MutableStateFlow<Resource<Unit>?> = MutableStateFlow(null)
    val loginFlow = _loginFlow.asStateFlow()

    fun login(authModel: AuthModel) = viewModelScope.launch{
        _loginFlow.value = Resource.Loading
        val result = authRepository.login(authModel)
        _loginFlow.value = result
    }


}