package com.example.myapplication.ui.profile


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts

import androidx.core.widget.addTextChangedListener

import com.example.myapplication.databinding.FragmentProfileBinding
import com.example.myapplication.extensions.loadImage
import com.example.myapplication.utils.Preferences



class ProfileFragment : Fragment() {
    private lateinit var binding: FragmentProfileBinding
    private lateinit var preferences: Preferences


    var mGetContent: ActivityResultLauncher<String> = registerForActivityResult(
        ActivityResultContracts.GetContent()) {uri ->

        binding.imgProfile.loadImage(uri.toString())
        Preferences(requireContext()).setImageProfile(uri.toString())
    }



            override fun onCreateView(
                inflater: LayoutInflater,
                container: ViewGroup?,
                savedInstanceState: Bundle?
            ): View? {
                binding = FragmentProfileBinding.inflate(inflater, container, false)
                initViews()
                initListeners()

                return binding.root
            }

            private fun initListeners() {
                binding.imgProfile.setOnClickListener {
                  mGetContent.launch("image/*")

                }
            }

            private fun initViews() {


                binding.imgProfile.loadImage(Preferences(requireContext()).getImageProfile().toString())
            }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        preferences = Preferences(requireContext())
        binding.etName.setText(preferences.getName())
        binding.etName.addTextChangedListener{
            preferences.saveName(binding.etName.text.toString())
        }
    }

        }