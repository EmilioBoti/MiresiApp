package com.example.miresiapp.businessLogic.createPost

import android.content.Context
import com.example.miresiapp.R
import com.example.miresiapp.businessLogic.createPost.IPost.ViewPresenter
import com.example.miresiapp.models.City
import com.example.miresiapp.models.Post
import com.example.miresiapp.models.Residence
import com.example.miresiapp.models.Room
import com.example.miresiapp.utils.toast
import kotlinx.coroutines.withContext

class CreatePostImpl(private val viewer: ViewPresenter, private val model: PostDataProvider, val context: Context): IPost.Presenter {
    private var list: MutableList<Residence>? = null
    private var listCities: MutableList<City>? = null
    private var listRooms: MutableList<Room>? = null
    private var userId: Int? = null

    override suspend fun requestResi(city: String) {
        list = model.getResiFromCity(city)
        list?.let {
            viewer.setDropDown(list)
        }
    }

    override suspend fun requestCities() {
        listCities = model.getAllCities()
        listCities?.let {
            viewer.setCitiesValues(listCities)
        }
    }

    override suspend fun requestRooms(id: Int) {
        listRooms = model.getRooms(id)
        listRooms?.let {
            viewer.setRoomsValues(it)
        }
    }

    override suspend fun createPost(resiId: Int, roomId: Int, dateStart: String, dateEnd: String) {
        if (validDate(dateStart) && validDate(dateEnd)){
            val post = Post(getDataUser()!!, resiId, roomId,dateStart, dateEnd)
            val d = model.insertPost(post)
            d
        }else viewer.errorValidDate("Date must be format YYYY-MM-DD or YYYY/MM/DD")
    }

    private fun getDataUser(): Int? {
        val prefe = context.getSharedPreferences(context.resources.getString(R.string.pref_loged_user), Context.MODE_PRIVATE)
        userId = prefe?.getInt("userId", 0)
        return userId
    }

    private fun validDate(date: String): Boolean {
        var d = date
        val regex = """^\d{4}-(0[1-9]|1[0-2])-(0[1-9]|[12][0-9]|3[01])${'$'}""".toRegex()
        /*if (date.contains("/") || date.contains(".")){
            d = ""
            date.forEach { if (it == '/') d+= "-" else d += it }
        }*/
        return (regex.matches(date))
    }

}