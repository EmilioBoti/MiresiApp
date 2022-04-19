package com.example.miresiapp.businessLogic.createPost

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.Filter
import android.widget.Filterable
import android.widget.TextView
import com.example.miresiapp.R
import com.example.miresiapp.models.City
import com.example.miresiapp.models.Residence
import com.example.miresiapp.models.Room

class DropDownRoom(private val list: MutableList<Room>): BaseAdapter(), Filterable {

    override fun getCount(): Int = list.size

    override fun getItem(position: Int): Room = list[position]

    override fun getItemId(position: Int): Long = list[position].id.toLong()

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val view = LayoutInflater.from(parent?.context).inflate(R.layout.drop_down_item, null)
        binData(view, position)
        return view
    }
    private fun binData(view: View, pos: Int){
        view.findViewById<TextView>(R.id.resiname).text = list[pos].name
    }

    override fun getFilter(): Filter {
        TODO("Not yet implemented")
    }
}