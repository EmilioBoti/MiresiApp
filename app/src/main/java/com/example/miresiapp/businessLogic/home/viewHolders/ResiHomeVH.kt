package com.example.miresiapp.businessLogic.home.viewHolders

import android.content.Context
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.miresiapp.R
import com.example.miresiapp.interfaces.OnClickItemView
import com.example.miresiapp.models.Residence
import com.squareup.picasso.Picasso

class ResiHomeVH(itemView: View, private val listener: OnClickItemView): RecyclerView.ViewHolder(itemView) {
    private val resiName: TextView = itemView.findViewById(R.id.resiNameH)
    private val imgResi: ImageView = itemView.findViewById(R.id.imgResi)
    private val nameCity: TextView = itemView.findViewById(R.id.nameCity)

    fun binData(residence: Residence?) {
        residence?.let {
            resiName.text = it.resiName
            nameCity.text = it.city
            Picasso.get().load(it.image).fit().centerCrop().into(imgResi)
        }

        imgResi.setOnClickListener {
            listener.onClickItem(absoluteAdapterPosition, it)
        }
    }
}