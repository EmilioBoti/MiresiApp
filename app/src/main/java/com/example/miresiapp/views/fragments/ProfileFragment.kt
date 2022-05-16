package com.example.miresiapp.views.fragments

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.view.Gravity
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RelativeLayout
import androidx.activity.result.ActivityResultCallback
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.lifecycle.lifecycleScope
import com.example.miresiapp.R
import com.example.miresiapp.businessLogic.chat.ChatDataProvider
import com.example.miresiapp.businessLogic.login.ILogin
import com.example.miresiapp.businessLogic.profile.EditProvider
import com.example.miresiapp.businessLogic.profile.ProfilePresenterImpl
import com.example.miresiapp.databinding.FragmentProfileBinding
import com.example.miresiapp.models.User
import com.example.miresiapp.utils.LocalData
import com.example.miresiapp.utils.toast
import com.squareup.picasso.Picasso
import kotlinx.coroutines.launch

class ProfileFragment : Fragment(), ILogin.PresenterView {
    private lateinit var binding: FragmentProfileBinding
    private lateinit var model: ChatDataProvider
    private lateinit var profilePresenter: ProfilePresenterImpl
    private var userId: Int? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_profile, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentProfileBinding.bind(view)

        userId = LocalData.getCurrentUserId(activity?.applicationContext!!)

        model = ChatDataProvider()
        profilePresenter = ProfilePresenterImpl(this, model)

        userId?.let {
            lifecycleScope.launch {
                profilePresenter.requestChats(it)
            }
        }
        eventsClick()
    }
    private fun eventsClick(){
        binding.goToCountry.setOnClickListener {
            navToEditScreen("country")
        }
        binding.goToGender.setOnClickListener {
            navToEditScreen("gender")
        }
        binding.goToAge.setOnClickListener {
            navToEditScreen("age")
        }
        binding.btnGoBack.setOnClickListener {
            activity?.onBackPressed()
        }

        binding.userImage.setOnClickListener {
        }
    }
    private fun navToEditScreen(key: String){
        /*val frag: Fragment? = activity?.supportFragmentManager?.findFragmentById(R.id.fragContainer)
        if (frag == null){
            activity?.supportFragmentManager
                ?.beginTransaction()
                ?.setCustomAnimations(R.anim.slide_in,R.anim.fade_out, R.anim.fade_in, R.anim.slide_out)
                ?.addToBackStack(key)
                ?.replace(R.id.fragContainer, EditProfileFragment(), key)
                ?.commit()
        }*/
        Intent(activity, EditProfileActivity::class.java).apply {
            putExtra("key", key)
            startActivity(this)
        }

    }

    override fun login(user: User?) {
        user?.let {
            Picasso.get().load(LocalData.getImageUser(activity?.applicationContext!!)).fit().centerCrop()
                .into(binding.userImage)
            binding.userName.text = it.name
            binding.email.text = it.email
            binding.country.text = it.country
            binding.gender.text = it.gender
            binding.age.text = it.age?.toString()
        }
    }

    override fun error() {
        TODO("Not yet implemented")
    }
}