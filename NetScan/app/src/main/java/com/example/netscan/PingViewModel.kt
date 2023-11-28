package com.example.netscan

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.io.BufferedReader
import java.io.InputStreamReader

class PingViewModel : ViewModel() {

    private var pingResult = ""
    private val _result: MutableLiveData<String> = MutableLiveData()
    val result: LiveData<String>
        get() = _result

    fun pingIP(url: String){
        _result.value = ""
        pingResult = ""
        val runtime = Runtime.getRuntime()
        viewModelScope.launch {
            val proc = withContext(Dispatchers.IO) {
                runtime.exec("/system/bin/ping -c 10 $url")
            }

            val stdInput = BufferedReader(InputStreamReader(proc.inputStream))

            val stdError = BufferedReader(InputStreamReader(proc.errorStream))

            // Read the output from the command
            Log.d("testing","Here is the standard output of the command:\n")
            var s: String?
            while (withContext(Dispatchers.IO) {
                    stdInput.readLine()
                }.also { s = it } != null) {
                s?.let {
                    pingResult+=it+"\n"
                    _result.value = pingResult
                }
            }


            // Read any errors from the attempted command
            Log.d("testing","Here is the standard error of the command (if any):\n")

            while (withContext(Dispatchers.IO) {
                    stdError.readLine()
                }.also { s = it } != null) {
                s?.let {
                    pingResult+=it
                    _result.value = pingResult
                }
            }
        }

    }
}