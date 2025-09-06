package com.app.amerodeh.falconssoftandroidjopexamcleanarchitecture.data

import android.util.Log
import android.widget.Toast
import com.app.amerodeh.falconssoftandroidjopexamcleanarchitecture.data.datasource.ItemCacheDataSource
import com.app.amerodeh.falconssoftandroidjopexamcleanarchitecture.data.datasource.ItemLocalDataSource
import com.app.amerodeh.falconssoftandroidjopexamcleanarchitecture.data.datasource.ItemRemoteDataSource
import com.app.amerodeh.falconssoftandroidjopexamcleanarchitecture.data.model.ItemDisplay
import com.app.amerodeh.falconssoftandroidjopexamcleanarchitecture.domain.repository.ItemRepository

class ItemRepositoryImpl(
    private val itemRemoteDataSource: ItemRemoteDataSource,
    private val itemLocalDataSource: ItemLocalDataSource,
    private val itemCacheDataSource: ItemCacheDataSource
) : ItemRepository {

    override suspend fun getItems(): List<ItemDisplay>? {
        return getItemsFromCache()
    }

    override suspend fun updateItems(): List<ItemDisplay>? {
        val newListOfItems = getItemsFromAPI()
        itemLocalDataSource.clearAll()
        itemLocalDataSource.saveItemsToDB(newListOfItems)
        itemCacheDataSource.saveItemsToCache(newListOfItems)
        return newListOfItems
    }

    override suspend fun searchItems(searchQuery: String): List<ItemDisplay>? {
        return try {
            val result = itemLocalDataSource.searchItemsInDB(searchQuery)
            result
        } catch (e: Exception) {
            Log.e("ItemRepository", "Error searching items: ${e.message}")
            emptyList()
        }    }

    private suspend fun getItemsFromAPI(): List<ItemDisplay> {
        var itemList: List<ItemDisplay> = emptyList()
        try {
            val responsel = itemRemoteDataSource.getItems()
            if (responsel != null) {
                itemList = responsel
            }
        } catch (exception: Exception) {
            // handle exception or log
            Log.e("ItemRepository", "Error getting items")

        }
        return itemList
    }

    private suspend fun getItemsFromCache(): List<ItemDisplay> {
        var itemList: List<ItemDisplay> = emptyList()
        try {
            itemList = itemCacheDataSource.getItemsFromCache()
        } catch (exception: Exception) {
            // handle exception or log

        }

        return if (itemList.isNotEmpty()) {
            itemList
        } else {
            itemList = getItemsFromLocal()
            itemCacheDataSource.saveItemsToCache(itemList)
            itemList
        }
    }

    private suspend fun getItemsFromLocal(): List<ItemDisplay> {
        var itemList: List<ItemDisplay> = emptyList()
        try {
            itemList = itemLocalDataSource.getItemsFromDB()
        } catch (exception: Exception) {
            // handle exception or log
        }

        return if (itemList.isNotEmpty()) {
            itemList
        } else {
            itemList = getItemsFromAPI()
            itemLocalDataSource.saveItemsToDB(itemList)
            itemList
        }
    }
}
