package com.example.rabindra.screens.models

import androidx.room.Entity
import androidx.room.PrimaryKey


data class Product(
    val status: Boolean,
    val message: String,
    val error: String?,
    val categories: ArrayList<Categories>
)

@Entity(tableName = "Categories")
data class Categories(@PrimaryKey val id: Int, val name: String, var items: ArrayList<Items>)
data class Items(
    val id: Int,
    val name: String,
    val icon: String,
    val price: Float,
    var isFavorite: Boolean = false,
    var isCart: Boolean = false,
    var productCount: Int = 1
)
