package com.example.miresiapp.views.fragments

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import androidx.appcompat.widget.SearchView
import androidx.core.widget.doOnTextChanged
import androidx.recyclerview.widget.ListAdapter
import com.example.miresiapp.R
import com.example.miresiapp.adapters.CityAdapter
import com.example.miresiapp.models.City
import com.example.miresiapp.views.activities.DashBoardActivity

class SearcherActivity : AppCompatActivity() {
    private lateinit var text1: TextView
    private lateinit var btnGoBack: ImageView
    private lateinit var searcher: SearchView
    private lateinit var text: String

    private val listSugges = mutableListOf<String>("sugges","test", "another test", "okahhaha", "ok heheh")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_searcher)

        init()

        searcher.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextChange(newText: String?): Boolean {
                this@SearcherActivity.text1.text = newText
                //Toast.makeText(applicationContext, newText, Toast.LENGTH_SHORT).show()
                return true
            }
            override fun onQueryTextSubmit(query: String?): Boolean {
                this@SearcherActivity.finish()
                Intent(applicationContext, DashBoardActivity::class.java).apply {
                    putExtra("id", 1)
                    startActivity(this)
                }
                //this@SearcherActivity.finish()
                return true
            }
        })
        btnGoBack.setOnClickListener {
            finish()
        }
    }
    private fun init(){
        btnGoBack = findViewById(R.id.btnGoBack)
        text1 = findViewById(R.id.text1)
        searcher = findViewById(R.id.searcher)
    }
    override fun onStart() {
        super.onStart()
    }
}