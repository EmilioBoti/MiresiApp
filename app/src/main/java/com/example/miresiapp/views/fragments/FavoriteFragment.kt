package com.example.miresiapp.views.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import com.example.miresiapp.R
import com.google.android.material.bottomnavigation.BottomNavigationView

class FavoriteFragment : Fragment() {
    private var fragContainer: FrameLayout? = null
    private var bottomNavigationView: BottomNavigationView? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_favorite, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        activity?.let {
            fragContainer = it.findViewById<FrameLayout>(R.id.fragContainer)
            bottomNavigationView = it.findViewById(R.id.bottom_navigation)
            bottomNavigationView?.menu?.findItem(R.id.pageFavorite)?.isChecked = true
        }
    }

}