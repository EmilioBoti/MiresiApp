package com.example.miresiapp.adapters

import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.miresiapp.R
import com.example.miresiapp.interfaces.OnClickItemView
import com.example.miresiapp.models.Residence
import com.squareup.picasso.Picasso

class ResiAdapter(private val listResi: MutableList<Residence>?, private val listener: OnClickItemView): RecyclerView.Adapter<ResiAdapter.ResiViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ResiViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.resi_item, null)
        return ResiViewHolder(view, listener)
    }

    override fun onBindViewHolder(holder: ResiViewHolder, position: Int) {
        holder.binData(listResi?.get(position))
    }

    override fun getItemCount(): Int = listResi!!.size

    class ResiViewHolder(itemView: View, private val listener: OnClickItemView): RecyclerView.ViewHolder(itemView) {
        val name: TextView = itemView.findViewById(R.id.nameResi)
       // val img: ImageView = itemView.findViewById(R.id.imgResi)
        val descrip: TextView = itemView.findViewById(R.id.descripResi)

        fun binData(residence: Residence?) {
            name.text = residence?.resiName
            //descrip.text = residence?.description

            itemView.setOnClickListener {
                if (RecyclerView.NO_POSITION != absoluteAdapterPosition ){
                    listener.onClickItem(absoluteAdapterPosition)
                }
            }
        }
    }
}