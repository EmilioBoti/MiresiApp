package com.example.miresiapp.businessLogic.createPost

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.example.miresiapp.R
import com.example.miresiapp.businessLogic.forum.IconModel
import com.example.miresiapp.models.City
import com.example.miresiapp.models.Residence

class DropDownAdapter(private val list: MutableList<IconModel>): BaseAdapter() {

    override fun getCount(): Int = list.size

    override fun getItem(position: Int): IconModel = list[position]

    override fun getItemId(position: Int): Long = position.toLong()

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val view = LayoutInflater.from(parent?.context).inflate(R.layout.drop_down_item, null)
        binData(view, position)
        return view
    }
    private fun binData(view: View, pos: Int) {
        view.findViewById<ImageView>(R.id.icon).setImageResource(list[pos].icon)
        view.findViewById<TextView>(R.id.resiname).text = list[pos].name
    }
}