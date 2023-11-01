package com.example.learncoroutines

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.learncoroutines.databinding.ActivityMainBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    // coroutine -> light weight threads
    //           -> scope(Lifetime of coroutine, like activity, fragment, view-model)
    //           -> Context (on which thread coroutine needs to be executed) -> Dispatchers to execute coroutine on a thread
    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        Log.d("ktFun", "activity -> ${Thread.currentThread().name}")

        binding.btnInc.setOnClickListener {
            binding.count.text = (binding.count.text.toString().toInt() + 1).toString()
            Log.d("ktFun", "inc -> ${Thread.currentThread().name}")
        }

        binding.btnExecLongRun.setOnClickListener {
            CoroutineScope(Dispatchers.IO).launch {
//                val j1 = CoroutineScope(Dispatchers.IO).async {
//                    getFbFollowers()
//                }
                val j1 = async { getFbFollowers() }

                val j2 = CoroutineScope(Dispatchers.IO).async {
                    getInstaFollowers()
                }

                Log.d("ktfun", "The fb : ${j1.await()} and insta : ${j2.await()}")
            }
        }
    }


    private suspend fun getFbFollowers(): Int {
        delay(2000)
        return 54
    }

    private suspend fun getInstaFollowers(): Int {
        delay(1000)
        return 113
    }
}