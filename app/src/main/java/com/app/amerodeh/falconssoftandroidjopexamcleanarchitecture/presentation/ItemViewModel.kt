package com.app.amerodeh.falconssoftandroidjopexamcleanarchitecture.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.app.amerodeh.falconssoftandroidjopexamcleanarchitecture.domain.usecases.GetItemsUseCases
import com.app.amerodeh.falconssoftandroidjopexamcleanarchitecture.domain.usecases.UpdateItemsUseCases

class ItemViewModel(
    private  val getItemsUseCases: GetItemsUseCases ,
    private val updateItemsUseCases: UpdateItemsUseCases

) :ViewModel() {
    fun  getItems() = liveData {
        val  itemList = getItemsUseCases.execute()
        emit(itemList)
    }
    fun updateItems()= liveData {
        val itemList =updateItemsUseCases.execute()
        emit(itemList)
    }

}