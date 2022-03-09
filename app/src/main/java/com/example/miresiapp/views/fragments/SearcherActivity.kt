package com.example.miresiapp.views.fragments

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import androidx.core.widget.doOnTextChanged
import androidx.recyclerview.widget.ListAdapter
import com.example.miresiapp.R
import com.example.miresiapp.adapters.CityAdapter
import com.example.miresiapp.models.City

class SearcherActivity : AppCompatActivity() {
    private lateinit var text1: TextView
    private lateinit var btnGoBack: ImageView
    private lateinit var searcher: EditText

    private val listSugges = mutableListOf<String>("sugges","test", "another test", "okahhaha", "ok heheh")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_searcher)
    }

    override fun onStart() {
        super.onStart()
        btnGoBack = findViewById(R.id.btnGoBack)
        text1 = findViewById(R.id.text1)
        searcher = findViewById(R.id.searcher)

        searcher.doOnTextChanged { text, start, before, count ->
            text1.text = text
        }

        btnGoBack.setOnClickListener {
            finish()
        }

    }
}