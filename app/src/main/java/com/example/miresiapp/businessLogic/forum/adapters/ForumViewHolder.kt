package com.example.miresiapp.businessLogic.forum.adapters

import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.miresiapp.R
import com.example.miresiapp.interfaces.OnClickItemView
import com.example.miresiapp.models.ForumModel

class ForumViewHolder(itemView: View, private val listener: OnClickItemView): RecyclerView.ViewHolder(itemView) {
    private val forumTitle: TextView = itemView.findViewById(R.id.forumTitle)
    private val forumDate: TextView = itemView.findViewById(R.id.forumDate)
    private val tagContainer: LinearLayout = itemView.findViewById(R.id.tagContainer)
    private val textsize: Float = 16f
    private val dimenRM: Int = 12
    private val dimenRP: Int = 10
    private val dimenTP: Int = 3
    private val dimenB: Int = 0

    private fun setView(text: String ): TextView {
        val margin: ViewGroup.MarginLayoutParams = itemView.layoutParams as ViewGroup.MarginLayoutParams
        margin.rightMargin = dimenRM

        val view = TextView(itemView.context).apply {
            this.text = text
            this.background = itemView.context.resources.getDrawable(R.drawable.tag_border)
            this.setPadding(dimenRP,dimenTP, dimenRP, dimenTP)
            this.textSize = textsize
            this.setTextColor(itemView.context.resources.getColor(R.color.black))
            this.layoutParams = margin
        }
        return view
    }

    fun bindData(forum: ForumModel){
        forumTitle.text = forum.forumName
        forumDate.text = forum.dateCreated

        forum.cityName?.apply {
            tagContainer.addView(setView(this))
        }
        forum.resiName?.apply {
            tagContainer.addView(setView(this))
        }
        forum.tag?.apply {
            tagContainer.addView(setView(this))
        }
        forum.secondLabel.apply {
            tagContainer.addView(setView(this))
        }

        itemView.setOnClickListener {
            if (RecyclerView.NO_POSITION != absoluteAdapterPosition){
                listener.onClickItem(absoluteAdapterPosition, it)
            }
        }
    }
}