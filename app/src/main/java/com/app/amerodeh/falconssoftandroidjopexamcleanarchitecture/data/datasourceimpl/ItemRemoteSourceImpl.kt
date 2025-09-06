package com.app.amerodeh.falconssoftandroidjopexamcleanarchitecture.data.datasourceimpl
import android.util.Log
import com.app.amerodeh.falconssoftandroidjopexamcleanarchitecture.data.api.ItemService
import com.app.amerodeh.falconssoftandroidjopexamcleanarchitecture.data.datasource.ItemRemoteDataSource
import com.app.amerodeh.falconssoftandroidjopexamcleanarchitecture.data.model.ItemDisplay


class ItemRemoteDataSourceImpl(
    private val itemService: ItemService,
    private val cono: Int,
    private val strno: Int,
) : ItemRemoteDataSource {

    override suspend fun getItems(): List<ItemDisplay> {
        val masterResponse = itemService.getItemsMaster(cono, strno)
        Log.d("RemoteDataSource", "Master raw response: ${masterResponse.body()}")

        val balanceResponse = itemService.getSalesManBalance(cono, strno)
        Log.d("RemoteDataSource", "Balance raw response: ${balanceResponse.body()}")

        if (!masterResponse.isSuccessful || masterResponse.body() == null) return emptyList()
        if (!balanceResponse.isSuccessful || balanceResponse.body() == null) return emptyList()

        val masters = masterResponse.body()!!.itemsMaster
        val balances = balanceResponse.body()!!.salesManItemsBalance
        val finalList = mutableListOf<ItemDisplay>()
        val currentTimeMillis :Long = System.currentTimeMillis()

        for (balance in balances) {
            val matchedItem = masters.find { it.itemNo == balance.itemCode }
            if (matchedItem != null) {
                finalList.add(
                    ItemDisplay(
                        id=0,
                        itemName = matchedItem.name,
                        category = matchedItem.categoryId,
                        qty = balance.qty.toDoubleOrNull() ?: 0.0,
                       lastSync =  currentTimeMillis

                    )
                )
            }
        }

        Log.d("RemoteDataSource", "Final list size: ${finalList.size}")
        return finalList
    }
}
