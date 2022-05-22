package com.example.miresiapp.views.fragments

import android.app.Dialog
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import com.example.miresiapp.R
import com.example.miresiapp.businessLogic.profile.ProfilePresenterImpl
import com.example.miresiapp.databinding.FragmentSettingBinding
import com.example.miresiapp.utils.LocalData
import com.example.miresiapp.views.activities.BaseRegister

class SettingFragment : Fragment() {
    private lateinit var binding: FragmentSettingBinding
    private lateinit var presenter: ProfilePresenterImpl

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_setting, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentSettingBinding.bind(view)

        binding.goToAccount.setOnClickListener {
            setProfileFrag()
        }

        binding.btnBack.setOnClickListener {
            activity?.onBackPressed()
        }
        binding.logout.setOnClickListener {
            confirm()
        }
    }


    private fun setProfileFrag(){
        activity?.supportFragmentManager?.beginTransaction()
            ?.replace(R.id.settingViewContainer, ProfileFragment())
            ?.addToBackStack(null)
            ?.commit()
    }

    private fun confirm(){
        activity?.let {
            AlertDialog.Builder(it).setTitle("")
                .setMessage("Are you sure want to log out?")
                .setPositiveButton("Accept", DialogInterface.OnClickListener{ dialogInterface, it ->
                    logout()
                })
                .setNegativeButton("Cancel", DialogInterface.OnClickListener { dialogInterface, i ->

                })
                .create()
                .show()

        }
    }

    private fun logout() {
        activity?.let {
            LocalData.removeData(it)
            it.finishAffinity()

            Intent(it, BaseRegister::class.java).apply {
                this.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                startActivity(this)
            }
        }
    }
}