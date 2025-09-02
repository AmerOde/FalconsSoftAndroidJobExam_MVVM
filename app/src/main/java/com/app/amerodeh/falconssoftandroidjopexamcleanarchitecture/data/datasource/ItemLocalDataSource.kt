package com.app.amerodeh.falconssoftandroidjopexamcleanarchitecture.data.datasource

import com.app.amerodeh.falconssoftandroidjopexamcleanarchitecture.data.model.ItemDisplay

interface ItemLocalDataSource {
    suspend fun getItemsFromDB():List<ItemDisplay>

    suspend fun saveItemsToDB(items:List<ItemDisplay>)

    suspend fun clearAll()
}