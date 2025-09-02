package com.app.amerodeh.falconssoftandroidjopexamcleanarchitecture.data.datasourceimpl

import com.app.amerodeh.falconssoftandroidjopexamcleanarchitecture.data.datasource.ItemCacheDataSource
import com.app.amerodeh.falconssoftandroidjopexamcleanarchitecture.data.model.ItemDisplay

class ItemCacheDataSourceImpl() :ItemCacheDataSource {
    private var itemList =ArrayList<ItemDisplay>()
    override suspend fun getItemsFromCache(): List<ItemDisplay> {

        return itemList
    }

    override suspend fun saveItemsToCache(items: List<ItemDisplay>) {

        itemList.clear()
        itemList =ArrayList(items)

    }
}