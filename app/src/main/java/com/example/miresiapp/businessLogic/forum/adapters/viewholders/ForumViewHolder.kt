package com.example.miresiapp.businessLogic.forum.adapters.viewholders

import android.content.res.ColorStateList
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.miresiapp.R
import com.example.miresiapp.interfaces.OnClickItemView
import com.example.miresiapp.models.forumModels.ForumModel

class ForumViewHolder(itemView: View, private val listener: OnClickItemView): RecyclerView.ViewHolder(itemView) {
    private val forumTitle: TextView = itemView.findViewById(R.id.forumTitle)
    private val forumDate: TextView = itemView.findViewById(R.id.forumDate)
    private val icon: ImageView = itemView.findViewById(R.id.iconContainer)
    private val tagContainer: LinearLayout = itemView.findViewById(R.id.tagContainer)
    private val textsize: Float = 16f
    private val dimenRM: Int = 12
    private val dimenRP: Int = 20
    private val dimenTP: Int = 8
    private val dimenB: Int = 0

    private fun setView(text: String ): TextView {
        val margin: ViewGroup.MarginLayoutParams = itemView.layoutParams as ViewGroup.MarginLayoutParams
        margin.rightMargin = dimenRM

        return TextView(itemView.context).apply {
            this.text = text
            this.setTextColor(ColorStateList.valueOf(ContextCompat.getColor(itemView.context, R.color.white)))
            this.background = ContextCompat.getDrawable(itemView.context, R.drawable.tag_border)
            this.setPadding(dimenRP,dimenTP, dimenRP, dimenTP)
            this.textSize = textsize
            this.layoutParams = margin
        }
    }

    fun bindData(forum: ForumModel){
        forumTitle.text = forum.forumName
        forumDate.text = forum.dateCreated
        tagContainer.removeAllViews()

        forum.image?.let {
            try {
                icon.setImageDrawable(ContextCompat.getDrawable(itemView.context, it))
            }catch (err: Exception){

            }
        }

        forum.cityName?.apply {
            tagContainer.addView(setView(this))
        }
        forum.resiName?.apply {
            tagContainer.addView(setView(this))
        }
        forum.categoryName?.apply {
            tagContainer.addView(setView(this))
        }

        itemView.setOnClickListener {
            if (RecyclerView.NO_POSITION != absoluteAdapterPosition){
                listener.onClickItem(absoluteAdapterPosition, it)
            }
        }
    }
}