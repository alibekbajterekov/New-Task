package com.example.myapplication.ui.authentification

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.FragmentActivity
import androidx.navigation.fragment.findNavController
import com.example.myapplication.R
import com.example.myapplication.databinding.FragmentAuthBinding
import com.example.myapplication.extensions.showToast
import com.google.firebase.FirebaseException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthOptions
import com.google.firebase.auth.PhoneAuthProvider
import com.google.firebase.auth.PhoneAuthProvider.verifyPhoneNumber
import java.util.concurrent.TimeUnit

class AuthFragment : Fragment() {

    private lateinit var binding: FragmentAuthBinding
    private var auth = FirebaseAuth.getInstance()
    private var correctCode: String? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAuthBinding.inflate(inflater, container, false)

        initViews()
        initListeners()

        return binding.root
    }

    private fun initListeners() {
        binding.btnSave.setOnClickListener{
            if (binding.etPhone.text!!.isNotEmpty()) {
                sendPhone()
                showToast("Отправка...")
            }else{
                binding.etPhone.error = "Введите номер телефона"
            }
        }
binding.btnConfirmCode.setOnClickListener{
    sendCode()
}
    }



    private fun initViews() {

    }
   private fun sendPhone() {
        auth.setLanguageCode("ru")
        val options = PhoneAuthOptions.newBuilder(auth)
            .setPhoneNumber(binding.etPhone.text.toString())
            .setTimeout(60L, TimeUnit.SECONDS)
            .setActivity(requireActivity())
            .setCallbacks(object: PhoneAuthProvider.OnVerificationStateChangedCallbacks(){
                override fun onVerificationCompleted(p0: PhoneAuthCredential) {
                    showToast("Успешно отправлено")
                }

                override fun onVerificationFailed(exception: FirebaseException) {
                    showToast(exception.message.toString())
                }

                override fun onCodeSent(verificationCode: String, p1: PhoneAuthProvider.ForceResendingToken) {
                    super.onCodeSent(verificationCode, p1)
                    correctCode = verificationCode
                    binding.etPhoneLayout.isVisible = false
                    binding.btnSave.isVisible = false

                    binding.etCodeLayout.isVisible = true
                    binding.btnConfirmCode.isVisible = true

                    Log.e("ololo", "onCodeSent: $verificationCode")
                }

            })
            .build()
        PhoneAuthProvider.verifyPhoneNumber(options)
    }

    private fun sendCode() {
        val credential =
            PhoneAuthProvider.getCredential(
                correctCode.toString(), binding.etCode.text.toString())
if (credential != null){
        signInWithAuthCredential(credential)
    }}
    private fun signInWithAuthCredential(credential: PhoneAuthCredential){
        auth.signInWithCredential(credential)
            .addOnCompleteListener(requireActivity()) { task ->
                if (task.isSuccessful) {
                    findNavController().navigate(R.id.navigation_home)

                }else{
                    if (task.exception is FirebaseAuthInvalidCredentialsException){
                        showToast(task.exception.toString())
                    }
                }
            }
    }
}
