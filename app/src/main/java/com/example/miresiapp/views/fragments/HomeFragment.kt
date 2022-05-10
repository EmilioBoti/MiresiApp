package com.example.miresiapp.views.fragments

import android.os.Bundle
import android.view.*
import android.widget.FrameLayout
import androidx.fragment.app.Fragment
import androidx.appcompat.widget.SearchView
import com.example.miresiapp.R
import com.example.miresiapp.businessLogic.home.IHome
import com.example.miresiapp.utils.toast

class HomeFragment : Fragment(), IHome.ViewPresenter {
    private var fragContainer: FrameLayout? = null
    private lateinit var searcher: SearchView

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        searcher = view.findViewById(R.id.searcher)
        fragContainer = activity?.findViewById<FrameLayout>(R.id.fragContainer)

        searcher.setOnSearchClickListener {

            activity?.supportFragmentManager?.beginTransaction()
                ?.setCustomAnimations(R.anim.slide_in,R.anim.fade_out, R.anim.fade_in, R.anim.slide_out)
                ?.add(R.id.fragContainer, SearchFragment(this))
                ?.addToBackStack("search")
                ?.commit()
        }
    }

    override fun navigateTo(name: String) {
        /*val data = Bundle().apply { putString("city", name) }
        residencesFragment = ResidenceFragment().apply { arguments = data }
        activity?.supportFragmentManager?.beginTransaction()
            ?.replace(R.id.viewContainer, residencesFragment)
            ?.addToBackStack("search")
            ?.commit()*/
    }

}