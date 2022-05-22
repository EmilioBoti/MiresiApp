package com.example.miresiapp.businessLogic.profile

import com.example.miresiapp.utils.LocalData

class EditPresenterImpl(private val viewer: IEdit.ViewPresenter, private val model: IEdit.ModelPresenter): IEdit.Presenter {

    override suspend fun requestUpdate(id: Int, map: HashMap<String, Any>) {
        val updated: Boolean? = model.updateUser(id, map)
        if (updated == true){
            viewer.updateUI(map)
        }
    }

}