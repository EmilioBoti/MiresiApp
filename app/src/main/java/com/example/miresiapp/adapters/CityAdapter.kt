package com.example.miresiapp.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.miresiapp.R
import com.example.miresiapp.interfaces.OnClickItemView
import com.example.miresiapp.models.City

class CityAdapter(private val listCity: MutableList<City>,private val context: Context,val listener: OnClickItemView): RecyclerView.Adapter<CityAdapter.CityViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CityViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.city_item, null)
        return CityViewHolder(view, listener)
    }

    override fun onBindViewHolder(holder: CityViewHolder, position: Int) {
        holder.bindData(listCity[position])
    }

    override fun getItemCount(): Int {
        return listCity.size
    }

    class CityViewHolder(itemView: View,private val listener: OnClickItemView): RecyclerView.ViewHolder(itemView){
        private val nameCity: TextView = itemView.findViewById(R.id.nameCity)
        //private val image: ImageView = itemView.findViewById(R.id.imgCity)

        fun bindData(city: City) {
            nameCity.text = city.name
            /*Picasso.get().load(city.image)
                .resize(200, 200)
                .centerCrop()
                .into(image)*/

            listener.let {
                if (absoluteAdapterPosition != RecyclerView.NO_POSITION)
                    itemView.setOnClickListener { view ->
                        listener.onClickItem(absoluteAdapterPosition, view)
                    }
            }
        }
    }
}