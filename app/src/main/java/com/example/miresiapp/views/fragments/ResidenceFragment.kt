package com.example.miresiapp.views.fragments

import android.content.Intent
import android.graphics.drawable.Icon
import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.annotation.RequiresApi
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.miresiapp.R
import com.example.miresiapp.adapters.ResiAdapter
import com.example.miresiapp.businessLogic.residence.DataProviderResi
import com.example.miresiapp.businessLogic.residence.IResi.PresenterView
import com.example.miresiapp.businessLogic.residence.ResiInteractorImpl
import com.example.miresiapp.interfaces.OnClickItemView
import com.example.miresiapp.models.CommentModel
import com.example.miresiapp.models.FavouriteModel
import com.example.miresiapp.models.Residence
import com.example.miresiapp.models.Room
import com.example.miresiapp.utils.LocalData
import com.example.miresiapp.utils.toast
import com.example.miresiapp.views.activities.ResiInfoActivity
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.launch

class ResidenceFragment : Fragment(), PresenterView, OnClickItemView {
    private var city: String? = null
    private lateinit var resiInteractorImpl: ResiInteractorImpl
    private lateinit var model: DataProviderResi
    private lateinit var recyclerView: RecyclerView
    private var listResi: MutableList<Residence>? = null
    private var userId: Int? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_residences, container, false)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerView = view.findViewById(R.id.resiContainer)
    }

    override fun onStart() {
        super.onStart()
        city = arguments?.getString("city")
        userId = LocalData.getCurrentUserId(activity?.applicationContext!!)

        model = DataProviderResi()
        resiInteractorImpl = ResiInteractorImpl(this, model)

        city?.let {
            lifecycleScope.launch {
                resiInteractorImpl.makeRequest(it)
            }
        }
    }

    override fun getResi(list: MutableList<Residence>?) {
        listResi = list
        val resiAdapter = ResiAdapter(listResi, this, activity?.applicationContext, userId!!)
        recyclerView.apply {
            layoutManager = LinearLayoutManager(activity, RecyclerView.VERTICAL, false)
            adapter = resiAdapter
        }
    }

    override fun setRooms(list: MutableList<Room>) {
        TODO("Not yet implemented")
    }

    override fun setComments(list: MutableList<CommentModel>) {
        TODO("Not yet implemented")
    }

    override fun added(susscces: String) {
        toast(activity, "It has been ${susscces}")
    }

    override fun error(err: String) {
        toast(activity?.applicationContext, "Error")
    }

    override fun onClickItem(pos: Int, view: View) {
        Intent(activity, ResiInfoActivity::class.java).apply {
            listResi?.let {
                this.putExtra("id", it[pos].id)
                startActivity(this)
            }
        }
    }

    @RequiresApi(Build.VERSION_CODES.M)
    override fun addFavoriteItem(pos: Int, view: View) {
        val image = view as ImageView
        val favouriteModel = FavouriteModel(userId!!, listResi?.get(pos)?.id!!)
        if (listResi?.get(pos)?.favouriteIdU == userId) {
            listResi?.get(pos)?.favouriteIdU = null
            image.setImageIcon(Icon.createWithResource(activity, R.drawable.favorite_border_24))
            lifecycleScope.launch {
                resiInteractorImpl.removeFavourite(favouriteModel)
            }
        }else {
            listResi?.get(pos)?.favouriteIdU = userId
            image.setImageIcon(Icon.createWithResource(activity, R.drawable.favorite_24))
            lifecycleScope.launch {
                resiInteractorImpl.addFavourite(favouriteModel)
            }
        }
    }
}