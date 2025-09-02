package com.app.amerodeh.falconssoftandroidjopexamcleanarchitecture.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "items_table")
data class ItemDisplay(
    @PrimaryKey(autoGenerate = true)
    val id:Int,

    val itemName :String,
    val category:String,
    val qty:Double,
)
