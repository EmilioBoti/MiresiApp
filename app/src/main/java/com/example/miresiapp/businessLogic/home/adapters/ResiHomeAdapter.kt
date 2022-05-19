package com.example.miresiapp.businessLogic.home.adapters

import android.content.Context
import android.os.Build
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import com.example.miresiapp.R
import com.example.miresiapp.adapters.viewHolders.ResiViewHolder
import com.example.miresiapp.businessLogic.home.viewHolders.ResiHomeVH
import com.example.miresiapp.interfaces.OnClickItemView
import com.example.miresiapp.models.Residence

class ResiHomeAdapter(private val listResi: MutableList<Residence>, private val listener: OnClickItemView
): RecyclerView.Adapter<ResiHomeVH>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ResiHomeVH {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.resi_item_home, null)
        return ResiHomeVH(view, listener)
    }

    override fun onBindViewHolder(holder: ResiHomeVH, position: Int) {
        holder.binData(listResi[position])
    }

    override fun getItemCount(): Int = listResi.size
}