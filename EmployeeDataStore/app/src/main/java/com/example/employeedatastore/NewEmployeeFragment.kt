package com.example.employeedatastore

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.employeedatastore.databinding.NewEmployeeFragmentBinding
import com.example.employeedatastore.employeeRoom.Employee

class NewEmployeeFragment : Fragment() {

    private lateinit var binding: NewEmployeeFragmentBinding
    private val viewModel: EmpViewModel by viewModels {
        EmployeeViewModelFactory((requireActivity().application as BaseApplication).repository)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = NewEmployeeFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnSubmit.setOnClickListener {
            viewModel.insertEmployee(
                Employee(
                    binding.empCode.editableText.toString(),
                    binding.empName.editableText.toString()
                )
            )
//            val action =
//                NewEmployeeFragmentDirections.actionNewEmployeeFragmentToEmployeesRecordFragment()
//            findNavController().navigate(action)
        }
    }
}