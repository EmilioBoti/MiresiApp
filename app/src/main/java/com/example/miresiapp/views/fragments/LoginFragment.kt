package com.example.miresiapp.views.fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import com.example.miresiapp.R
import com.example.miresiapp.businessLogic.login.ILoginInteractor
import com.example.miresiapp.businessLogic.login.LoginBusinessLogic
import com.example.miresiapp.views.MainBaseActivity

class LoginFragment : Fragment(), View.OnClickListener, ILoginInteractor.PresenterView {
    private lateinit var btnLogin: Button
    private lateinit var btnSignUp: Button
    private lateinit var loginBusinessLogic: LoginBusinessLogic

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_login, container, false)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init(view)

        btnLogin.setOnClickListener(this)
        btnSignUp.setOnClickListener(this)
    }

    private fun init(view: View) {
        btnLogin = view.findViewById(R.id.btnLogin)
        btnSignUp = view.findViewById(R.id.btnSignup)
        loginBusinessLogic = LoginBusinessLogic(this)
    }

    override fun onClick(v: View?) {
        when(v?.id){
            R.id.btnLogin ->{
                loginBusinessLogic.validUser()
            }
            R.id.btnSignup ->{
                activity?.supportFragmentManager?.beginTransaction()
                    ?.replace(R.id.loginRegisterContainer, SignUpFragment())
                    ?.addToBackStack("loginBack")
                    ?.commit()
            }
        }
    }

    override fun login() {
        Intent(activity?.applicationContext, MainBaseActivity::class.java).apply {
            startActivity(this)
            activity?.finish()
        }
    }
}