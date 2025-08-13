package com.example.littlelemon.data

data class Dish(
    val id: Int,
    val name: String,
    val description: String,
    val price: Double,
    val imageResId: Int,
    val category: String
)

val dishes = listOf<Dish>(
    Dish(
        id = 1,
        name = "Greek Salad",
        description = "The famous greek salad of crispy lettuce, peppers, olives, our Chicago.",
        price = 12.99,
        imageResId = com.example.littlelemon.R.drawable.greeksalad,
        category = "starters"
    ),
    Dish(
        id = 2,
        name = "Lemon Desert",
        description = "The famous greek salad of crispy lettuce, peppers, olives, our Chicago.",
        price = 12.99,
        imageResId = com.example.littlelemon.R.drawable.lemondessert,
        category = "desserts"
    ),
    Dish(
        id = 3,
        name = "Grilled Fish",
        description = "Our Bruschetta is made from grilled bread that has been smeared with garlic and seasoned with salt and olive oil.",
        price = 12.99,
        imageResId = com.example.littlelemon.R.drawable.grilledfish,
        category = "mains"
    ),
    Dish(
        id = 4,
        name = "Pasta",
        description = "The famous greek salad of crispy lettuce, peppers, olives, our Chicago.",
        price = 12.99,
        imageResId = com.example.littlelemon.R.drawable.pasta,
        category = "mains"
    ),
    Dish(
        id = 5,
        name = "Bruschetta",
        description = "Oven-baked bruschetta stuffed with tomatoes and herbs.",
        price = 12.99,
        imageResId = com.example.littlelemon.R.drawable.bruschetta,
        category = "starters"
    )
)