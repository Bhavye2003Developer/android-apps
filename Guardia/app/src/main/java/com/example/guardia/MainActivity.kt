package com.example.guardia

import android.annotation.SuppressLint
import android.app.usage.NetworkStats
import android.app.usage.NetworkStatsManager
import android.content.pm.ApplicationInfo
import android.content.pm.PackageManager
import android.net.ConnectivityManager
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.GregorianCalendar


@Suppress("DEPRECATED_IDENTITY_EQUALS")
class MainActivity : AppCompatActivity() {

    private lateinit var networkStatsManager: NetworkStatsManager
    private var bucket: NetworkStats.Bucket? = null

    @SuppressLint("QueryPermissionsNeeded")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        networkStatsManager =
            applicationContext.getSystemService(NETWORK_STATS_SERVICE) as NetworkStatsManager

//        startActivity(Intent(Settings.ACTION_USAGE_ACCESS_SETTINGS));

        val pm = packageManager
        val packages =
            pm.getInstalledApplications(PackageManager.GET_META_DATA) // all packages on android device

        val userInstalledApps: MutableList<ApplicationInfo> =
            mutableListOf() // to only add user installed app

        for (packageItem in packages) {
            if (packageItem.flags and ApplicationInfo.FLAG_SYSTEM !== 1) {
                userInstalledApps.add(packageItem)
            }
        }

        Toast.makeText(this, userInstalledApps.size.toString(), Toast.LENGTH_SHORT).show()
        val recyclerView = findViewById<RecyclerView>(R.id.UidrecylerView)

        recyclerView.adapter =
            UidRecyclerViewAdapter(this, userInstalledApps, ::getNetworkStatsUid)
    }


    @SuppressLint("SimpleDateFormat")
    @Suppress("DEPRECATION")
    fun getNetworkStatsUid(uid: Int) {
        var query: NetworkStats
        val currentDate = Calendar.getInstance()
//        val currentDay = currentDate.get(Calendar.DAY_OF_MONTH)
        val currentMonth = currentDate.get(Calendar.MONTH) // 0 based
        val currentYear = currentDate.get(Calendar.YEAR)

        val monthStart = Calendar.getInstance()
        val monthEnd = Calendar.getInstance()

        val dateFormat1 = SimpleDateFormat("dd-MM-yyyy")
        val dateFormat2 = SimpleDateFormat("dd-MM-yyyy")

        val cal: Calendar = GregorianCalendar()

        dateFormat1.timeZone = cal.timeZone
        dateFormat2.timeZone = cal.timeZone

        // Pair<Pair<year, month>, Pair<rxBytes, TxBytes>>
        val result: MutableList<Pair<Pair<Int, Int>, Pair<Long, Long>>> = mutableListOf()

        var month: Int
        var year = currentYear - 1
        MainScope().launch {
            for (i in 0..11) { // till previous month of current year

                month = (currentMonth + i) % 12
                monthStart.set(year, month, 1) // starting of month
                monthEnd.set(year, month, monthStart.getActualMaximum(Calendar.DAY_OF_MONTH))

//                Log.d(
//                    "testing",
//                    "monthStart -> ${
//                        dateFormat1.format(monthStart.time)
//                    }, monthEnd -> ${
//                        dateFormat1.format(monthEnd.time)
//                    }"
//                )

                // work
                query = networkStatsManager.queryDetailsForUid(
                    ConnectivityManager.TYPE_WIFI,
                    "",
                    monthStart.timeInMillis,
                    monthEnd.timeInMillis,
                    uid
                )
                bucket = NetworkStats.Bucket()
                query.getNextBucket(bucket)

                result.add(Pair(Pair(year, month), Pair(bucket!!.rxBytes, bucket!!.txBytes)))

                Log.d(
                    "testing",
                    "Month -> $month, rxBytes -> ${bucket!!.rxBytes}, txBytes -> ${bucket!!.txBytes}"
                )

                if (month == 11) ++year
            }
        }

        if (result.size > 0) {
            Log.d("testing", result.toString())
        }
    }
}