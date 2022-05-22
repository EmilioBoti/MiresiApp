package com.example.miresiapp.businessLogic.forum


import android.content.Context
import com.example.miresiapp.models.City
import com.example.miresiapp.models.forumModels.Forum
import com.example.miresiapp.models.forumModels.ForumModel
import com.example.miresiapp.models.Residence
import com.example.miresiapp.utils.LocalData
import com.example.miresiapp.utils.toast

open class ForumLogicImpl(private val viewer: IForum.ViewPresenter, private val model: ForumProvider,
                          private val context: Context
): IForum.Presenter {
    private var list: MutableList<ForumModel>? = null
    private var listCategory: MutableList<CategoryModel>? = null
    private var listCities: MutableList<City>? = null
    private var listResis: MutableList<Residence>? = null
    var forum: Forum

    init {
        forum = Forum(name = "", LocalData.getCurrentUserId(context)!!)
    }

    fun clickedForum(pos: Int) {
        list?.let {
            viewer.showForumReply(it[pos].forumId)
        }
    }

    override suspend fun requestForums() {
        list = model.getForums()
        list?.let {
            viewer.showForums(it)
        }
    }

    override suspend fun requestCategories() {
        listCategory = mutableListOf()
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

    override suspend fun getResis(position: Int, city: String) {
        listResis = model.getResiFromCity(city)
        listResis?.let { resi ->
            forum.cityId = listCities?.let { it[position].id }
            viewer.setResis(resi)
        }
    }

    override suspend fun requestCities() {
        listCities = model.getAllCities()
        listCities?.let {
            viewer.setCities(it)
        }
    }

    override suspend fun publish(name: String) {
        this.forum.name = name
        if (forum.resiId != 0 && forum.cityId != 0 &&
            forum.categoryId != 0 && forum.creatorUser != 0
        ){
            val isPublish = model.publishForum(forum)
            toast(this.context , isPublish)
        }else viewer.error("error")
    }

    fun getResiId(id: Int) {
        listResis?.let {
            forum.resiId = it[id].id
        }
    }

    fun setCateId(cateId: Int?) {
        this.forum.categoryId = cateId
    }

    fun setIconId(icon: Int) {
        forum.image = icon
    }
}