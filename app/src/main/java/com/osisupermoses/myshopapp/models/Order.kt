package com.osisupermoses.myshopapp.models

import android.os.Parcelable

/**
 * A data model class for Order item with required fields.
 */
@kotlinx.parcelize.Parcelize
data class Order(
    val user_id: String = "",
    val items: ArrayList<CartItem> = ArrayList(),
    val address: Address = Address(),
    val title: String = "",
    val image: String = "",
    val sub_total_amount: String = "",
    val shipping_charge: String = "",
    val total_amount: String = "",
    val order_datetime: Long = 0L,
    var id: String = ""
) : Parcelable