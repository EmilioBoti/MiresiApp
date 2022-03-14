package com.example.miresiapp.views.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.miresiapp.R
import com.example.miresiapp.adapters.ResiAdapter
import com.example.miresiapp.businessLogic.residence.DataProviderResi
import com.example.miresiapp.businessLogic.residence.IResi.PresenterView
import com.example.miresiapp.businessLogic.residence.ResiInteractorImpl
import com.example.miresiapp.interfaces.OnClickItemView
import com.example.miresiapp.models.DataProvider
import com.example.miresiapp.models.Residence
import kotlinx.coroutines.launch

class ResidencesFragment : Fragment(), PresenterView, OnClickItemView {
    private lateinit var city: String
    private lateinit var resiInteractorImpl: ResiInteractorImpl
    private lateinit var model: DataProviderResi
    private lateinit var recyclerView: RecyclerView
    private var listResi: MutableList<Residence>? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_residences, container, false)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerView = view.findViewById(R.id.resiContainer)
    }

    override fun onStart() {
        super.onStart()
        city = arguments?.getString("city", "")!!
        model = DataProviderResi()
        resiInteractorImpl = ResiInteractorImpl(this, model)

        lifecycleScope.launch {
            resiInteractorImpl.makeRequest(city)
        }
    }

    override fun getResi(list: MutableList<Residence>?) {
        listResi = list
        val resiAdapter = ResiAdapter(listResi, this)
        recyclerView.apply {
            layoutManager = LinearLayoutManager(activity, RecyclerView.VERTICAL, false)
            adapter = resiAdapter
        }
    }

    override fun error(err: String) {
        toast(err)
    }

    private fun <T>toast(obj: T){
        Toast.makeText(activity, obj.toString(), Toast.LENGTH_SHORT).show()
    }

    override fun onClickItem(pos: Int) {

    }
}