package com.app.amerodeh.falconssoftandroidjopexamcleanarchitecture.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.app.amerodeh.falconssoftandroidjopexamcleanarchitecture.domain.usecases.GetItemsUseCases
import com.app.amerodeh.falconssoftandroidjopexamcleanarchitecture.domain.usecases.SearchItemUseCases
import com.app.amerodeh.falconssoftandroidjopexamcleanarchitecture.domain.usecases.UpdateItemsUseCases

class ItemViewModel(
    private  val getItemsUseCases: GetItemsUseCases ,
    private val updateItemsUseCases: UpdateItemsUseCases,
    private  val searchItemUseCases: SearchItemUseCases

) :ViewModel() {
    fun  getItems() = liveData {
        val  itemList = getItemsUseCases.execute()
        emit(itemList)
    }
    fun updateItems()= liveData {
        val itemList =updateItemsUseCases.execute()
        emit(itemList)
    }
    fun searchItems(query: String) = liveData {
        val items = searchItemUseCases.execute(query)
        emit(items)
    }


}