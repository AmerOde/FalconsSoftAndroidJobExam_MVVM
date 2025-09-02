package com.app.amerodeh.falconssoftandroidjopexamcleanarchitecture.data.datasourceimpl

import com.app.amerodeh.falconssoftandroidjopexamcleanarchitecture.data.datasource.ItemLocalDataSource
import com.app.amerodeh.falconssoftandroidjopexamcleanarchitecture.data.model.ItemDisplay
import com.app.amerodeh.falconssoftandroidjopexamcleanarchitecture.data.roomdb.ItemDao
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ItemLocalDataSourceImpl(private val itemDao: ItemDao):ItemLocalDataSource {
    override suspend fun getItemsFromDB(): List<ItemDisplay> {
        return itemDao.getItems()
    }

    override suspend fun saveItemsToDB(items: List<ItemDisplay>) {

            itemDao.saveItem(items)

    }

    override suspend fun clearAll() {


                itemDao.deleteAllItems()




    }
}