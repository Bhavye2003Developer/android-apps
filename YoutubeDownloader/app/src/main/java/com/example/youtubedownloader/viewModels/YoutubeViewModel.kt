package com.example.youtubedownloader.viewModels

import android.annotation.SuppressLint
import android.app.Application
import android.app.DownloadManager
import android.content.Context
import android.content.Context.DOWNLOAD_SERVICE
import android.database.Cursor
import android.net.Uri
import android.os.Environment
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.youtubedownloader.roomdb.YoutubeDatabase
import com.example.youtubedownloader.roomdb.YoutubeURL
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import org.jsoup.Jsoup
import java.io.File
import java.util.Date


class YoutubeViewModel(private val application: Application) : AndroidViewModel(application) {
    // url processing
    private var database: YoutubeDatabase = YoutubeDatabase.getInstance(application)
    private fun insertUrl(youtubeURL: YoutubeURL){
        viewModelScope.launch {
            database.youtubeDao().insertUrl(youtubeURL)
        }
    }
    fun processURL(url: String){

        when (isValidUrl(url)) {
            2 -> {
                CoroutineScope(Dispatchers.IO).launch {
                    val baseUri: Deferred<String> = async { Jsoup.connect(url).get().baseUri()}
                    val updatedURL = baseUri.await().replace("youtube", "youpak")
                    val doc = Jsoup.connect(updatedURL).get()

                    val info = getVideoInfo(doc)
                    val videoURL = info.second.second
                    val videoTitle = info.first

                    val downloadInfo = downloadVideo(application.baseContext, videoURL, videoTitle)
                    val downloadId = downloadInfo.second
                    val videoName = downloadInfo.first
                    getDownloadStatus(downloadId, application.baseContext, videoName)

                    Log.d("testing", info.first)
                }
            }
            1 -> {
                val updatedURL = url.replace("youtube", "youpak")
                CoroutineScope(Dispatchers.IO).launch {
                    val doc = Jsoup.connect(updatedURL).get()

                    val info = getVideoInfo(doc)
                    val videoURL = info.second.second
                    val videoTitle = info.first

                    val downloadInfo = downloadVideo(application.baseContext, videoURL, videoTitle)
                    val downloadId = downloadInfo.second
                    val videoName = downloadInfo.first
                    getDownloadStatus(downloadId, application.baseContext, videoName)

                    Log.d("testing", info.first)
                }
            }
            else -> {
                Log.d("testing", "Not valid url")
            }
        }
    }
    @SuppressLint("Range")
    private fun getDownloadStatus(downloadId: Long, baseActivity: Context, videoTitle: String = "No title found") {

        val dm: DownloadManager = baseActivity.getSystemService(DOWNLOAD_SERVICE) as DownloadManager

        viewModelScope.launch {
            var downloading = true
            while (downloading) {
                val q: DownloadManager.Query = DownloadManager.Query()
                q.setFilterById(downloadId)
                val cursor: Cursor = dm.query(q)
                cursor.moveToFirst()
                val bytes_downloaded = cursor.getInt(
                    cursor.getColumnIndex(DownloadManager.COLUMN_BYTES_DOWNLOADED_SO_FAR)
                )
                val bytes_total =
                    cursor.getInt(cursor.getColumnIndex(DownloadManager.COLUMN_TOTAL_SIZE_BYTES))
                if (cursor.getInt(cursor.getColumnIndex(DownloadManager.COLUMN_STATUS)) == DownloadManager.STATUS_SUCCESSFUL) {
                    downloading = false
                }
//                val dl_progress = (bytes_downloaded * 100L)/bytes_total
                Log.d("testing", "status -> ${statusMessage(cursor)}, progress -> $bytes_downloaded total -> $bytes_total")

                if (statusMessage(cursor)=="Download complete!"){
                    val youtubeURL = YoutubeURL(videoTitle = videoTitle, downloadDate = Date().time)
                    insertUrl(youtubeURL)
                }
                cursor.close()
            }
        }
    }
    @SuppressLint("Range")
    private fun statusMessage(c: Cursor): String {
        val msg: String = when (c.getInt(c.getColumnIndex(DownloadManager.COLUMN_STATUS))) {
                DownloadManager.STATUS_FAILED -> "Download failed!"
                DownloadManager.STATUS_PAUSED -> "Download paused!"
                DownloadManager.STATUS_PENDING -> "Download pending!"
                DownloadManager.STATUS_RUNNING -> "Download in progress!"
                DownloadManager.STATUS_SUCCESSFUL -> "Download complete!"
                else -> "Download is nowhere in sight"
            }
        return msg
    }
    private fun isValidUrl(url: String) : Int{
        if ("https://youtube.com/" in url){
            return 1
        }
        else if ("https://youtu.be/" in url){
            return 2
        }
        return 0
    }
    private fun getVideoInfo(doc : org.jsoup.nodes.Document): Pair<String, Pair<String, String>> {

        // Pair(title, Pair(videoQuality, videoURL))

        var videoTitle = doc.title()
        val sliced = videoTitle.split("Clipzag.com")[0]
        videoTitle = sliced.slice(0..sliced.length-3)

        val aTags = doc.body().getElementsByClass("btn-group btn-group-justified")[0]
            .getElementsByTag("a")

        return if (aTags.size>0){
            val video = aTags[0]
            val videoUrl = video.attr("href")
            val videoQuality = video.text()

            Pair(videoTitle, Pair(videoQuality, videoUrl))
        } else{
            Pair("",Pair("",""))
        }
    }
    private fun downloadVideo(baseActivity: Context, url: String, videoTitle: String): Pair<String, Long> {
        val direct = File(Environment.getExternalStorageDirectory().toString() + "/yt_downloads")
        if (!direct.exists()) {
            direct.mkdirs()
        }
        val downloadReference: Long
        val dm: DownloadManager = baseActivity.getSystemService(DOWNLOAD_SERVICE) as DownloadManager
        val uri = Uri.parse(url)
        val request = DownloadManager.Request(uri)
        request.setDestinationInExternalPublicDir(
            "/yt_downloads",
            "$videoTitle.mp4"
        )
        request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED)
        downloadReference = dm.enqueue(request)

        return Pair("$videoTitle.mp4", downloadReference)
    }
}
@Suppress("UNCHECKED_CAST")
class YoutubeViewModelFactory(private val application: Application) : ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(YoutubeViewModel::class.java)) {
            YoutubeViewModel(application) as T
        } else {
            throw IllegalArgumentException("ViewModel Not Found")
        }
    }
}