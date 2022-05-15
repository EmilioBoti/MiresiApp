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
import com.example.miresiapp.R
import com.example.miresiapp.databinding.FragmentProfileBinding
import com.example.miresiapp.utils.LocalData
import com.example.miresiapp.utils.toast
import com.squareup.picasso.Picasso

class ProfileFragment : Fragment() {
    private lateinit var binding: FragmentProfileBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_profile, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentProfileBinding.bind(view)

        Picasso.get().load(LocalData.getImageUser(activity?.applicationContext!!))
            .fit()
            .centerCrop()
            .into(binding.userImage)

        //binding.email.text = LocalData.getEmailUser(activity?.applicationContext!!)
        //binding.userName.text = LocalData.getCurrentUserName(activity?.applicationContext!!)
        //binding.country.text = LocalData.getCurrentUserCountry(activity?.applicationContext!!)
        //binding.age.text = LocalData.getCurrentUserAge(activity?.applicationContext!!, "age")

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
}