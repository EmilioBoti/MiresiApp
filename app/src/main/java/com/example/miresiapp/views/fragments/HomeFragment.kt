package com.example.miresiapp.views.fragments

import android.content.Intent
import android.os.Bundle
import android.view.*
import android.widget.FrameLayout
import androidx.fragment.app.Fragment
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.miresiapp.R
import com.example.miresiapp.businessLogic.home.HomePresenter
import com.example.miresiapp.businessLogic.home.HomeProvider
import com.example.miresiapp.businessLogic.home.IHome
import com.example.miresiapp.businessLogic.home.adapters.ResiHomeAdapter
import com.example.miresiapp.businessLogic.residence.adapters.RoomAdapter
import com.example.miresiapp.databinding.FragmentHomeBinding
import com.example.miresiapp.interfaces.OnClickItemView
import com.example.miresiapp.models.Residence
import com.example.miresiapp.models.Room
import com.example.miresiapp.utils.toast
import com.example.miresiapp.views.activities.ResiInfoActivity
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.coroutines.launch

class HomeFragment : Fragment(), IHome.ViewPresenter, OnClickItemView {
    private lateinit var binding: FragmentHomeBinding
    private var fragContainer: FrameLayout? = null
    private lateinit var bottomNavigationView: BottomNavigationView
    private lateinit var model: HomeProvider
    private lateinit var homePresenter: HomePresenter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentHomeBinding.bind(view)

        fragContainer = activity?.findViewById<FrameLayout>(R.id.fragContainer)
        bottomNavigationView = activity?.findViewById(R.id.bottom_navigation)!!
        bottomNavigationView.menu.findItem(R.id.pageHome).isChecked = true

        model = HomeProvider()
        homePresenter = HomePresenter(this, model)

        lifecycleScope.launch {
            homePresenter.requestResis(10)
        }
        lifecycleScope.launch {
            homePresenter.requestRooms(10)
        }

        binding.searcher.setOnSearchClickListener {
            navigateTo("")
        }
    }

    override fun navigateTo(name: String) {
        activity?.supportFragmentManager?.beginTransaction()
            ?.setCustomAnimations(R.anim.slide_in,R.anim.fade_out, R.anim.fade_in, R.anim.slide_out)
            ?.add(R.id.fragContainer, SearchFragment(this))
            ?.addToBackStack("search")
            ?.commit()
    }

    override fun setResi(list: MutableList<Residence>) {
        val resiAdapter = ResiHomeAdapter(list, this)
        binding.resiHomeContainer.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(activity, RecyclerView.HORIZONTAL, false)
            adapter = resiAdapter
        }
    }

    override fun setRooms(list: MutableList<Room>) {
        val resiAdapter = RoomAdapter(list, this)
        binding.roomsContainer.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(activity, RecyclerView.HORIZONTAL, false)
            adapter = resiAdapter
        }
    }

    override fun navigateToResi(id: Int) {
        Intent(activity, ResiInfoActivity::class.java).apply {
            putExtra("id", id)
            startActivity(this)
        }
    }

    override fun onClickItem(pos: Int, view: View) {
        homePresenter.naveTo(pos)
    }

    override fun addFavoriteItem(pos: Int, view: View) {
    }

}