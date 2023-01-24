package com.example.myapplication.ui.home.NewTask

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.setFragmentResult
import androidx.fragment.app.setFragmentResultListener
import androidx.navigation.fragment.findNavController
import com.example.myapplication.R
import com.example.myapplication.databinding.FragmentHomeBinding
import com.example.myapplication.databinding.FragmentNewTaskBinding


class NewTaskFragment : Fragment() {

    private lateinit var binding: FragmentNewTaskBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentNewTaskBinding.inflate(inflater,container,false)

        initViews()
        initListeners()
        return binding.root
    }

    private fun initViews() {


    }

    private fun initListeners() {
binding.btnSave.setOnClickListener{
    setFragmentResult("new_task", bundleOf(
        "title" to binding.etTitle.text.toString(),
        "desc" to binding.etDesc.text.toString()
    ))
    findNavController().navigateUp()


}
    }

}