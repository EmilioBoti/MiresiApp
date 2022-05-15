package com.example.miresiapp.adapters.viewHolders

import android.content.Context
import android.content.res.ColorStateList
import android.content.res.Resources
import android.graphics.drawable.Drawable
import android.graphics.drawable.Icon
import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.GridLayout
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.core.content.ContextCompat
import androidx.core.view.marginRight
import androidx.recyclerview.widget.RecyclerView
import com.example.miresiapp.R
import com.example.miresiapp.interfaces.OnClickItemView
import com.example.miresiapp.models.Residence
import com.squareup.picasso.Picasso

class ResiViewHolder(itemView: View, private val listener: OnClickItemView,
                     private val applicationContext: Context?,
                     private val userId: Int
): RecyclerView.ViewHolder(itemView) {
    private val name: TextView = itemView.findViewById(R.id.nameResi)
    private val location: TextView = itemView.findViewById(R.id.location)
    private val img: ImageView = itemView.findViewById(R.id.imgResi)
    private val accessTo: GridLayout = itemView.findViewById(R.id.accessTo)
    private val favorite: ImageView = itemView.findViewById(R.id.addFavorite)
    private val priceFrom: TextView = itemView.findViewById(R.id.priceFrom)

    @RequiresApi(Build.VERSION_CODES.M)
    fun binData(residence: Residence?) {
        name.text = "Residencia Universitaria ${residence?.resiName}"
        location.text = residence?.location
        priceFrom.text = residence?.priceFrom?.let { "€ $it" } ?: "--"

        if (userId == residence?.favouriteIdU){
            favorite.setImageIcon(Icon.createWithResource(applicationContext, R.drawable.favorite_24))
        }
        if (residence?.gym != 0) {
            accessTo.addView(createImage(R.drawable.fitness_center_24))
        }
        if (residence?.parking_car != 0){
            accessTo.addView(createImage(R.drawable.local_parking_24))
        }

        Picasso.get().load(residence?.image)
            //.placeholder(R.drawable.resa_investigadors)
            .fit().centerCrop().into(img)

        favorite.setOnClickListener { view ->
            if(RecyclerView.NO_POSITION != absoluteAdapterPosition){
                listener.addFavoriteItem(absoluteAdapterPosition, view)
            }
        }
        itemView.setOnClickListener { view ->
            if (RecyclerView.NO_POSITION != absoluteAdapterPosition ){
                listener.onClickItem(absoluteAdapterPosition, view)
            }
        }
    }

    @RequiresApi(Build.VERSION_CODES.M)
    private fun createImage(drawable: Int): ImageView{

        val margin = LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        margin.rightMargin = 25

        return ImageView(itemView.context).apply {
            this.setImageIcon(Icon.createWithResource(itemView.context, drawable))
            this.imageTintList = ColorStateList.valueOf(ContextCompat.getColor(itemView.context,R.color.green_turquoise))
            this.layoutParams = margin
        }

    }
}