package com.screen.doorgelezen

import android.app.Activity
import android.app.Application
import android.os.Bundle
import android.view.WindowManager
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import com.screen.doorgelezen.data.di.ConnectivityObserver
import com.screen.doorgelezen.utils.raiseToast
import dagger.hilt.android.HiltAndroidApp
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltAndroidApp
class Doorgelezen : Application() {

    @Inject
    lateinit var connectivityObserver: ConnectivityObserver

    @Inject
    lateinit var connectivityStatus: ConnectivityObserver.Status

    companion object {
        var mIsConnected = false
        var fetchData = MutableLiveData(false)
    }

    private var canReadConnection = false


    override fun onCreate() {
        super.onCreate()
        observeConnectivity()
    }

    private fun observeConnectivity() {
        // Start observing the connectivity changes using coroutines
        val scope = CoroutineScope(Dispatchers.Unconfined)

        scope.launch {
            mIsConnected = connectivityStatus == ConnectivityObserver.Status.AVAILABLE
            if (!mIsConnected) {
                canReadConnection = true
            }
            CoroutineScope(Dispatchers.Main).launch {
                handleConnectivityChange(connectivityStatus)
            }

            connectivityObserver.observe().collect { status ->
                handleConnectivityChange(status)
                if (mIsConnected && canReadConnection) {
                    CoroutineScope(Dispatchers.Main).launch {
                        raiseToast(
                            this@Doorgelezen,
                            getString(R.string.BACK_ONLINE),
                            Toast.LENGTH_SHORT
                        )
                        fetchData.postValue(true)
                    }
                }
                if (!mIsConnected) {
                    canReadConnection = true
                    CoroutineScope(Dispatchers.Main).launch {
                        fetchData.postValue(false)
                    }
                }
            }

        }
    }

    private fun handleConnectivityChange(status: ConnectivityObserver.Status) {


        when (status) {
            ConnectivityObserver.Status.LOST -> {
                raiseToast(
                    this@Doorgelezen,
                    getString(R.string.INTERNET_CONNECTION_LOST),
                    Toast.LENGTH_SHORT
                )
                mIsConnected = false
            }

            ConnectivityObserver.Status.LOSING -> {
                raiseToast(
                    this@Doorgelezen,
                    getString(R.string.LOSING_INTERNET_CONNECTION),
                    Toast.LENGTH_SHORT
                )
                mIsConnected = false
            }

            ConnectivityObserver.Status.UNAVAILABLE -> {
                raiseToast(
                    this@Doorgelezen,
                    getString(R.string.NO_INTERNET_CONNECTION),
                    Toast.LENGTH_SHORT
                )
                mIsConnected = false
            }

            ConnectivityObserver.Status.AVAILABLE -> {
                mIsConnected = true
            }
        }
    }

}
