package com.app.amerodeh.falconssoftandroidjopexamcleanarchitecture.data.model

import com.google.gson.annotations.SerializedName

data class ApiResponse(
    @SerializedName("SalesMan_Items_Balance")
    val salesManItemsBalance: List<SalesManItemBalance>,

    @SerializedName("Items_Master")
    val itemsMaster: List<ItemMaster>
)