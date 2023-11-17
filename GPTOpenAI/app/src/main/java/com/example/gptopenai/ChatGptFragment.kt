package com.example.gptopenai

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.gptopenai.chatRecyclerView.ChatRecyclerViewAdapter
import com.example.gptopenai.databinding.ChatGptFragmentBinding
import com.example.gptopenai.gptViewModel.GptViewModel

class ChatGptFragment : Fragment(R.layout.chat_gpt_fragment) {

    private lateinit var binding: ChatGptFragmentBinding
    private lateinit var viewModel: GptViewModel
    private var query: String = ""

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = ChatGptFragmentBinding.inflate(layoutInflater)
        viewModel = ViewModelProvider(this)[GptViewModel::class.java]
        return binding.root
    }

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnSendQuery.setOnClickListener {
            query = binding.query.editableText.toString()

            binding.userQuery.text = "Your query - $query"
            binding.query.text.clear()

            binding.progressBar.visibility = View.VISIBLE
            viewModel.submitUserQuery(query)

        }

        binding.chatsRecyclerView.setHasFixedSize(true)
        binding.chatsRecyclerView.layoutManager = LinearLayoutManager(context)

        viewModel.listChats.observe(viewLifecycleOwner) {
            binding.chatsRecyclerView.adapter = ChatRecyclerViewAdapter(it)
            binding.progressBar.visibility = View.GONE
        }
    }
}