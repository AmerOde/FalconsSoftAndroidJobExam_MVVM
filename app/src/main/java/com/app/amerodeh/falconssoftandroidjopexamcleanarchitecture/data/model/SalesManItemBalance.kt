package com.app.amerodeh.falconssoftandroidjopexamcleanarchitecture.data.model

import com.google.gson.annotations.SerializedName

data class SalesManItemBalance(
    @SerializedName("ItemOCode")
    val itemCode: String,

    @SerializedName("QTY")
    val qty: String,

    @SerializedName("COMAPNYNO")
    val companyNo: Int,

    @SerializedName("STOCK_CODE")
    val stockCode: Int,



)
