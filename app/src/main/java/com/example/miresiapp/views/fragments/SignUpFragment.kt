package com.example.miresiapp.views.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.lifecycle.lifecycleScope
import com.example.miresiapp.R
import com.example.miresiapp.businessLogic.registe.ISignUp
import com.example.miresiapp.businessLogic.registe.SignUpBusinessLogic
import com.example.miresiapp.businessLogic.registe.SignUpProvider
import com.example.miresiapp.models.RegisterUser
import com.example.miresiapp.models.User
import kotlinx.coroutines.launch

class SignUpFragment : Fragment(), ISignUp.ViewPresenter {
    private lateinit var nameInput: EditText
    private lateinit var emailInput: EditText
    private lateinit var pwInput: EditText
    private lateinit var confimPwInput: EditText
    private lateinit var btnSignUp: Button
    private lateinit var signUpProvider: SignUpProvider
    private lateinit var signUpBusinessLogic: SignUpBusinessLogic

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_sign_up, container, false)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        nameInput = view.findViewById(R.id.nameInput)
        emailInput = view.findViewById(R.id.emailInput)
        pwInput = view.findViewById(R.id.passwordInput)
        confimPwInput = view.findViewById(R.id.confimPw)
        btnSignUp = view.findViewById(R.id.btnSignup)
    }

    override fun onStart() {
        super.onStart()

        signUpProvider = SignUpProvider()
        signUpBusinessLogic = SignUpBusinessLogic(this, signUpProvider)

        btnSignUp.setOnClickListener {
            val newUser = RegisterUser(nameInput.text.toString().trim(), emailInput.text.toString().trim(),
                pwInput.text.toString().trim(), confimPwInput.text.toString().trim())

            lifecycleScope.launch {
                signUpBusinessLogic.resgisterUser(newUser)
            }
        }

    }

    override fun isCreated(newUser: User) {
        activity?.supportFragmentManager?.popBackStack()
    }

}