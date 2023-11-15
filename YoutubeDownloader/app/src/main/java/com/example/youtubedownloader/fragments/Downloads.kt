package com.example.youtubedownloader.fragments

import android.annotation.SuppressLint
import android.content.ContentResolver
import android.content.Context
import android.os.Build
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import com.example.youtubedownloader.YTViewAdapter
import com.example.youtubedownloader.databinding.FragmentDownloadsBinding

class Downloads : Fragment() {

    private lateinit var binding: FragmentDownloadsBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentDownloadsBinding.inflate(inflater, container, false)
        return binding.root
    }

    @SuppressLint("SetTextI18n")
    @RequiresApi(Build.VERSION_CODES.Q)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Example usage:
        val directoryPath = Environment.getExternalStoragePublicDirectory("")?.absolutePath + "/yt_downloads"
        val filesList = context?.let { getFilesInDirectory(it, directoryPath) }

        if (filesList!=null) {
            binding.ytDownloadedVideosRecyclerView.adapter =
                context?.let { YTViewAdapter(it, filesList) }
            binding.errorText.visibility = View.GONE
        }
        else{
            binding.errorText.text = "No video downloaded"
            binding.errorText.visibility = View.VISIBLE
        }
    }

    @RequiresApi(Build.VERSION_CODES.Q)
    fun getFilesInDirectory(context: Context, directoryPath: String): List<String> {
        val contentResolver: ContentResolver = context.contentResolver
        val uri = MediaStore.Files.getContentUri(MediaStore.VOLUME_EXTERNAL)
        // Define the columns you want to retrieve
        val projection = arrayOf(
            MediaStore.Files.FileColumns.DATA,  // The file path
            MediaStore.Files.FileColumns.MIME_TYPE
        )
        // Specify the directory you want to query
        val selection = "${MediaStore.Files.FileColumns.DATA} like ?"
        val selectionArgs = arrayOf("$directoryPath%")
        // Sort the results by file name or date, as needed
        val sortOrder = "${MediaStore.Files.FileColumns.DATE_MODIFIED} DESC"
        val cursor = contentResolver.query(uri, projection, selection, selectionArgs, sortOrder)
        val filesList = mutableListOf<String>()
        cursor?.use {
            val dataColumnIndex = it.getColumnIndex(MediaStore.Files.FileColumns.DATA)
            while (it.moveToNext()) {
                val filePath = it.getString(dataColumnIndex)
                filesList.add(filePath)
            }
        }
        return filesList
    }
}