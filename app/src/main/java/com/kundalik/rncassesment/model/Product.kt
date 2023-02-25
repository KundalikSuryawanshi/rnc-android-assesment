package com.kundalik.rncassesment.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Product(
    val brand: String,
    val description: String,
    val id: Int,
    val image_link: String,
    val name: String,
    val price: String,
    val website_link: String,
    val product_link: String
) : Parcelable
