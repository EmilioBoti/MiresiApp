package com.example.miresiapp.adapters

import android.content.Context
import android.graphics.drawable.Icon
import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.GridLayout
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import com.example.miresiapp.R
import com.example.miresiapp.adapters.viewHolders.ResiViewHolder
import com.example.miresiapp.interfaces.OnClickItemView
import com.example.miresiapp.models.Residence
import com.squareup.picasso.Picasso

class ResiAdapter(private val listResi: MutableList<Residence>?, private val listener: OnClickItemView,
    private  val applicationContext: Context?, private val userId: Int
): RecyclerView.Adapter<ResiViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ResiViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.resi_item, null)
        return ResiViewHolder(view, listener, applicationContext, userId)
    }

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onBindViewHolder(holder: ResiViewHolder, position: Int) {
        holder.binData(listResi?.get(position))
    }

    override fun getItemCount(): Int = listResi!!.size

}