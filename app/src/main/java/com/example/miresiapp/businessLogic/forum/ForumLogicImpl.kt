package com.example.miresiapp.businessLogic.forum

import android.content.Context
import android.content.res.ColorStateList
import android.widget.CompoundButton
import androidx.core.content.ContextCompat
import com.example.miresiapp.R
import com.example.miresiapp.models.City
import com.example.miresiapp.models.ForumModel
import com.google.android.material.chip.Chip

class ForumLogicImpl(private val viewer: IForum.ViewPresenter,private val model: ForumProvider,
): IForum.Presenter {
    private var list: MutableList<ForumModel>? = null
    private var listCategory: MutableList<CategoryModel>? = null
    private var listCities: MutableList<City>? = null

    override suspend fun requestForums() {
        list = model.getForums()
        list?.let {
            viewer.showForums(it)
        }
    }

    override suspend fun requestCategories() {
        listCategory = model.getCategories()
        listCategory?.let {
            viewer.setChips(it)
        }
    }

    override suspend fun filterForums(name: String) {
        list = model.getFilterForums(name)
        list?.let {
            viewer.showForums(it)
        }
    }

    override fun resis(city: String) {

    }

    override suspend fun requestCities() {
        listCities = model.getAllCities()
        listCities?.let {
            viewer.setCities(it)
        }
    }
}