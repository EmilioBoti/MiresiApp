package com.example.miresiapp.views.fragments

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.annotation.RequiresApi
import androidx.lifecycle.lifecycleScope
import com.example.miresiapp.R
import com.example.miresiapp.businessLogic.profile.EditPresenterImpl
import com.example.miresiapp.businessLogic.profile.EditProvider
import com.example.miresiapp.businessLogic.profile.IEdit
import com.example.miresiapp.databinding.ActivityEditProfileBinding
import com.example.miresiapp.utils.LocalData
import com.example.miresiapp.utils.toast
import kotlinx.coroutines.launch

class EditProfileActivity : AppCompatActivity(), IEdit.ViewPresenter {
    private lateinit var binding: ActivityEditProfileBinding
    private lateinit var map: HashMap<String, Any>
    private var key: String? = null
    private lateinit var model: EditProvider
    private lateinit var editPresenterImpl: EditPresenterImpl

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEditProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)

        key = intent?.extras?.getString("key")
        binding.input.apply {
            hint = key
        }
    }

    override fun onStart() {
        super.onStart()

        model = EditProvider()
        editPresenterImpl = EditPresenterImpl(this, model)
        map = HashMap<String, Any>()

        binding.cancel.setOnClickListener {
            onBackPressed()
        }

        binding.apply.setOnClickListener {
            key?.let {
                map.set(it, binding.input.text.toString())
                lifecycleScope.launch {
                    editPresenterImpl.requestUpdate(LocalData.getCurrentUserId(applicationContext)!!, map)
                }
            }
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun updateUI(map: HashMap<String, Any>) {
        val type = map[key]
        LocalData.saveUserCountry(applicationContext, key!! ,map[key].toString())
    }
}