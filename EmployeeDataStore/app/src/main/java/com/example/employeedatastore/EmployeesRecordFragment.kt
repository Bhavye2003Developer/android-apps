package com.example.employeedatastore

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.example.employeedatastore.databinding.AllEmployeesRecordsFragmentBinding

class EmployeesRecordFragment : Fragment() {

    private lateinit var binding: AllEmployeesRecordsFragmentBinding

    private val viewModel: EmpViewModel by viewModels {
        EmployeeViewModelFactory((requireActivity().application as BaseApplication).repository)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = AllEmployeesRecordsFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnAddEmp.setOnClickListener {
            val action =
                EmployeesRecordFragmentDirections.actionEmployeesRecordFragmentToNewEmployeeFragment()
            findNavController().navigate(action)
        }

        viewModel.allEmployees.observe(viewLifecycleOwner, Observer {
            var result = ""
            it.forEach { emp ->
                result += "${emp.employeeId} -> ${emp.name}\n"
            }
            binding.result.text = result
        })
    }
}