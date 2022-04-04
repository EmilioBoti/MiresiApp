package com.example.miresiapp.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.GridLayout
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.miresiapp.R
import com.example.miresiapp.interfaces.OnClickItemView
import com.example.miresiapp.models.Residence
import com.squareup.picasso.Picasso

class ResiAdapter(private val listResi: MutableList<Residence>?, private val listener: OnClickItemView,
    private  val applicationContext: Context?
): RecyclerView.Adapter<ResiAdapter.ResiViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ResiViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.resi_item, null)
        return ResiViewHolder(view, listener, applicationContext)
    }

    override fun onBindViewHolder(holder: ResiViewHolder, position: Int) {
        holder.binData(listResi?.get(position))
    }

    override fun getItemCount(): Int = listResi!!.size

    class ResiViewHolder(itemView: View, private val listener: OnClickItemView,private val applicationContext: Context?): RecyclerView.ViewHolder(itemView) {
        private val name: TextView = itemView.findViewById(R.id.nameResi)
        private val location: TextView = itemView.findViewById(R.id.location)
        private val img: ImageView = itemView.findViewById(R.id.imgResi)
        private val accessTo: GridLayout = itemView.findViewById(R.id.accessTo)
        private val favorite: ImageView = itemView.findViewById(R.id.addFavorite)
        private val priceFrom: TextView = itemView.findViewById(R.id.priceFrom)

        fun binData(residence: Residence?) {
            name.text = residence?.resiName
            location.text = residence?.location
            priceFrom.text = residence?.priceFrom?.let { "€ $it" } ?: "--"

            if (residence?.gym != 0) LayoutInflater.from(applicationContext).inflate(R.layout.gym_layout, accessTo)
            if (residence?.parking_car != 0) LayoutInflater.from(applicationContext).inflate(R.layout.parking_icon_layout, accessTo)

            Picasso.get().load(residence?.image)
                //.placeholder(R.drawable.resa_investigadors)
                .fit().centerCrop().into(img)

            favorite.setOnClickListener { view ->
                if(RecyclerView.NO_POSITION != absoluteAdapterPosition){
                    listener.addFavoriteItem(absoluteAdapterPosition, view)
                }
            }
            itemView.setOnClickListener {
                if (RecyclerView.NO_POSITION != absoluteAdapterPosition ){
                    listener.onClickItem(absoluteAdapterPosition)
                }
            }
        }
    }
}