package com.app.amerodeh.falconssoftandroidjopexamcleanarchitecture.data.model
import com.google.gson.annotations.SerializedName

data class ItemMaster(

    @SerializedName("ITEMNO")
    val itemNo: String,

    @SerializedName("NAME")
    val name: String,

    @SerializedName("CATEOGRYID")
    val categoryId: String,

    @SerializedName("BARCODE")
    val barcode: String,



    @SerializedName("LSPRICE")
    val price: Double

//    val BARCODE: String,
//    val F_D: String,
//    val ISAPIPIC: String,
//    val ISSUSPENDED: String,
//    val ITEMHASSERIAL: String,
//    val ITEML: String,
//    val ITEMNO: String,
//    val ITEMPICSPATH: String,
//    val ItemK: String,
//    val LSPRICE: String,
//    val MINPRICE: String,
//    val TAXPERC: String
)