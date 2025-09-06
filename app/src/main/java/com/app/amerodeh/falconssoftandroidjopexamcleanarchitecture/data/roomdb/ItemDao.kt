package com.app.amerodeh.falconssoftandroidjopexamcleanarchitecture.data.roomdb

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import com.app.amerodeh.falconssoftandroidjopexamcleanarchitecture.data.model.ItemDisplay
import retrofit2.http.Query

@Dao
interface ItemDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
        suspend fun saveItem(items:List<ItemDisplay>)


     @androidx.room.Query("DELETE FROM items_table")
        suspend fun deleteAllItems()

     @androidx.room.Query("SELECT * FROM items_table")
        suspend fun getItems():List<ItemDisplay>
     @androidx.room.Query("SELECT * FROM items_table WHERE itemName LIKE '%' || :searchQuery || '%' OR category LIKE '%' || :searchQuery || '%'")
            suspend fun searchItems(searchQuery: String): List <ItemDisplay>

}