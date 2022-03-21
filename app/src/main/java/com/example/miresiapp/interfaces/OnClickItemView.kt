package com.example.miresiapp.interfaces

import android.view.View

interface OnClickItemView {
    fun onClickItem(pos: Int)
    fun addFavoriteItem(pos: Int, view: View)
}