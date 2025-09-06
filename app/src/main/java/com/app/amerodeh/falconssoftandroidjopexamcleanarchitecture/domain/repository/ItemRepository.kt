package com.app.amerodeh.falconssoftandroidjopexamcleanarchitecture.domain.repository

import com.app.amerodeh.falconssoftandroidjopexamcleanarchitecture.data.model.ItemDisplay

interface ItemRepository {

    suspend fun getItems():List<ItemDisplay>?
    suspend fun updateItems():List<ItemDisplay>?
    suspend fun searchItems(searchQuery:String) : List<ItemDisplay>?

}
