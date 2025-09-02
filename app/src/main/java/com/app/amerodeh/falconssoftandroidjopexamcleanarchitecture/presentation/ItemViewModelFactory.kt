package com.app.amerodeh.falconssoftandroidjopexamcleanarchitecture.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.app.amerodeh.falconssoftandroidjopexamcleanarchitecture.domain.usecases.GetItemsUseCases
import com.app.amerodeh.falconssoftandroidjopexamcleanarchitecture.domain.usecases.UpdateItemsUseCases

class ItemViewModelFactory(private val getItemsUseCases: GetItemsUseCases,
    private  val updateItemsUseCases: UpdateItemsUseCases):ViewModelProvider.Factory{

    override fun <T : ViewModel> create(modelClass: Class<T>): T {

        return ItemViewModel(getItemsUseCases,updateItemsUseCases)as T
    }

}