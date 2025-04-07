package com.shankhanilsaha.makemymeal

data class Categories(
    val idCategory: String,
    val strCategory: String,
    val strCategoryThumb: String,
    val strCategoryDescription: String
)

data class CategoryResponse(val categories: List<Categories>)
