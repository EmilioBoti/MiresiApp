package com.example.miresiapp.views.fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.miresiapp.R
import com.example.miresiapp.businessLogic.login.ILoginInteractor
import com.example.miresiapp.businessLogic.login.LoginBusinessLogic
import com.example.miresiapp.models.DataProvider
import com.example.miresiapp.models.User
import com.example.miresiapp.models.UserLogin
import com.example.miresiapp.views.activities.MainBaseActivity

class LoginFragment : Fragment(), View.OnClickListener, ILoginInteractor.PresenterView {
    private lateinit var emailInput: EditText
    private lateinit var pwInput: EditText
    private lateinit var btnLogin: Button
    private lateinit var btnSignUp: Button
    private lateinit var loginBusinessLogic: LoginBusinessLogic
    private lateinit var model: DataProvider

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
        emailInput = view.findViewById(R.id.emailInput)
        pwInput = view.findViewById(R.id.passwordInput)
        btnLogin = view.findViewById(R.id.btnLogin)
        btnSignUp = view.findViewById(R.id.btnSignup)
        model = DataProvider()
        loginBusinessLogic = LoginBusinessLogic(this, model)
    }

    override fun onClick(v: View?) {
        when(v?.id){
            R.id.btnLogin ->{
                loginBusinessLogic.validUser(UserLogin(emailInput.text.toString().trim(), pwInput.text.toString().trim()))
            }
            R.id.btnSignup ->{
                activity?.supportFragmentManager?.beginTransaction()
                    ?.replace(R.id.loginRegisterContainer, SignUpFragment())
                    ?.addToBackStack("loginBack")
                    ?.commit()
            }
        }
    }

    override fun login(user: User?) {

        Intent(activity?.applicationContext, MainBaseActivity::class.java).apply {
            startActivity(this)
            activity?.finish()
        }
    }

    override fun error() {
        Toast.makeText(activity, "Error to Valid the User", Toast.LENGTH_SHORT).show()
    }
}