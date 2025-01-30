package com.screen.doorgelezen.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.screen.doorgelezen.AppScreens
import com.screen.doorgelezen.data.models.AuthModel
import com.screen.doorgelezen.data.repository.AuthRepository
import com.screen.doorgelezen.data.repository.Resource
import com.screen.doorgelezen.utils.printDebug
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(private val authRepository: AuthRepository) : ViewModel() {

    private val _loginFlow: MutableStateFlow<Resource<Unit>?> = MutableStateFlow(null)
    val loginFlow = _loginFlow.asStateFlow()

    private val _isLoading: MutableStateFlow<Boolean> = MutableStateFlow(false)
    val isLoading = _isLoading.asStateFlow()

    fun login(authModel: AuthModel, showErrorSnackbar: () -> Unit) = viewModelScope.launch {
        _isLoading.value = true
        val result = authRepository.login(authModel)


        when (result) {
            is Resource.Failure -> {
                showErrorSnackbar()
                _isLoading.value = false
                printDebug("failed login")
            }

            Resource.Loading -> {
                _isLoading.value = true
                printDebug("loading")
            }

            is Resource.Success -> {
                _isLoading.value = false
                _loginFlow.value = result
                printDebug("success")
            }

        }
    }

    fun logout(showErrorSnackbar: (String?) -> Unit) = viewModelScope.launch {
        _isLoading.value = true
        val result = authRepository.logout()

        when (result) {
            is Resource.Failure -> {
                showErrorSnackbar(result.exception.message)
                _isLoading.value = false
                printDebug("failed login")
            }

            Resource.Loading -> {
                _isLoading.value = true
                printDebug("loading")
            }

            is Resource.Success -> {
                _isLoading.value = false
                _loginFlow.value = result
                printDebug("success")
            }

        }

    }

}