package com.app.amerodeh.falconssoftandroidjopexamcleanarchitecture.data.datasource

import com.app.amerodeh.falconssoftandroidjopexamcleanarchitecture.data.api.ItemService
import com.app.amerodeh.falconssoftandroidjopexamcleanarchitecture.data.model.ItemDisplay
import retrofit2.Response

interface ItemRemoteDataSource {
    suspend fun getItems(): List<ItemDisplay>
}
