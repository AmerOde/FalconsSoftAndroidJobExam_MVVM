package com.app.amerodeh.falconssoftandroidjopexamcleanarchitecture.data.datasource

import com.app.amerodeh.falconssoftandroidjopexamcleanarchitecture.data.model.ItemDisplay

interface ItemCacheDataSource {
    suspend fun getItemsFromCache():List<ItemDisplay>

    suspend fun saveItemsToCache(items:List<ItemDisplay>)
}