package com.example.miresiapp.views.fragments

import android.content.Intent
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.appcompat.widget.SearchView
import com.example.miresiapp.R
import com.example.miresiapp.utils.toast
import com.example.miresiapp.views.activities.SearcherActivity

class HomeFragment : Fragment() {
    private lateinit var searcher: SearchView

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        toast(activity, "Home")
        searcher = view.findViewById(R.id.searcher)

        searcher.setOnSearchClickListener {
            Intent(activity, SearcherActivity::class.java).apply {
                startActivity(this)
            }
        }
    }

}