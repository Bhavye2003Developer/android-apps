package com.example.quotify

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.quotify.databinding.QuoteMainBinding

class QuoteFragment : Fragment() {

    private lateinit var binding: QuoteMainBinding
    private lateinit var viewModel: QuoteViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = QuoteMainBinding.inflate(inflater, container, false)
        viewModel = ViewModelProvider(this)[QuoteViewModel::class.java]

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnNextQuote.setOnClickListener {
            viewModel.nextQuote()
            Log.d("Imp", "NEXT")
        }

        binding.btnPrevQuote.setOnClickListener {
            viewModel.prevQuote()
            Log.d("Imp", "PREVIOUS")
        }

        binding.btnShare.setOnClickListener {
            val intent = Intent(Intent.ACTION_SEND)
            intent.apply {
                // The intent does not have a URI, so declare the "text/plain" MIME type
                type = "text/plain"
                putExtra(Intent.EXTRA_TEXT, "Quote : ${binding.quoteText.text}\nAuthor : ${binding.quoteAuthor.text}")
            }
            startActivity(intent)
        }

        viewModel.quoteText.observe(viewLifecycleOwner, Observer {
            binding.quoteText.text = it
        })

        viewModel.quoteAuthor.observe(viewLifecycleOwner, Observer {
            binding.quoteAuthor.text = it
        })
    }
}