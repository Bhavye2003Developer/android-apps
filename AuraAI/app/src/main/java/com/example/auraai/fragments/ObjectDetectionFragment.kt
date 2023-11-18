package com.example.auraai.fragments

import android.annotation.SuppressLint
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.example.auraai.apiServices.ObjectDetectionApiService
import com.example.auraai.databinding.FragmentObjectDetectionBinding
import okhttp3.MediaType
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.ByteArrayOutputStream
import java.io.IOException
import java.io.InputStream

@Suppress("DEPRECATION")
class ObjectDetectionFragment : Fragment() {

    private lateinit var binding: FragmentObjectDetectionBinding
    private val args: ObjectDetectionFragmentArgs by navArgs()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentObjectDetectionBinding.inflate(inflater, container, false)
        return binding.root
    }

    @SuppressLint("NewApi")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val imageUri = Uri.parse(args.savedImageUri)
        val bitmap = MediaStore.Images.Media.getBitmap(requireActivity().contentResolver, imageUri)

        binding.resultantImage.setImageBitmap(bitmap)

        val apiService = ObjectDetectionApiService

        val iStream: InputStream = requireActivity().contentResolver.openInputStream(imageUri)!!
        val inputData: ByteArray = getBytes(iStream)!!

        val requestBody = RequestBody.create(MediaType.parse("application/octet"),
            inputData)

        val call = apiService.analyseImage(
            requestBody
        )

        call.enqueue(object : Callback<String?> {
            override fun onResponse(call: Call<String?>, response: Response<String?>) {
                response.body()?.let { Toast.makeText(requireActivity().applicationContext, it, Toast.LENGTH_SHORT).show() }
            }
            override fun onFailure(call: Call<String?>, t: Throwable) {
                Toast.makeText(requireActivity().applicationContext, t.message, Toast.LENGTH_SHORT).show()
            }
        })
    }

    @Throws(IOException::class)
    fun getBytes(inputStream: InputStream): ByteArray? {
        val byteBuffer = ByteArrayOutputStream()
        val bufferSize = 1024
        val buffer = ByteArray(bufferSize)
        var len: Int
        while (inputStream.read(buffer).also { len = it } != -1) {
            byteBuffer.write(buffer, 0, len)
        }
        return byteBuffer.toByteArray()
    }
}