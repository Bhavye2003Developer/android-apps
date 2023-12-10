package com.example.cipherchat.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import com.example.cipherchat.adapters.MessageViewAdapter
import com.example.cipherchat.databinding.FragmentGlobalChatBinding
import com.example.cipherchat.utils.Message
import com.example.cipherchat.viewModels.GlobalMessageViewModel

class GlobalChatFragment : Fragment() {

    private lateinit var binding: FragmentGlobalChatBinding
    private lateinit var viewModel: GlobalMessageViewModel
    private val args: GlobalChatFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentGlobalChatBinding.inflate(inflater, container, false)
        viewModel = ViewModelProvider(this)[GlobalMessageViewModel::class.java]
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val username = args.username

        binding.btnSendMessage.setOnClickListener {
            val newMessageText = binding.newMessage.editableText.toString()
            binding.newMessage.editableText.clear() // to clear editText

            if (newMessageText.isNotEmpty()) {
                val message = Message(newMessageText, username)
                viewModel.writeNewMessage(message)
            }
        }

        binding.messages.setHasFixedSize(true)

        viewModel.allMessages.observe(viewLifecycleOwner) {
            binding.messages.adapter = MessageViewAdapter(it)
        }
    }
}