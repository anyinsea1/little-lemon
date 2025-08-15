package com.example.littlelemon.data

data class Dish(
    val id: Int,
    val name: String,
    val description: String,
    val price: Double,
    val imageResId: Int,
    val category: String
)

/*
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
*/
val dishes = listOf<Dish>(
    Dish(1, "Greek Salad", "The famous Greek salad of crispy lettuce, peppers, olives, our Chicago.", 12.99, com.example.littlelemon.R.drawable.greeksalad, "starters"),
    Dish(2, "Lemon Dessert", "A delicious lemon dessert with a tangy flavor, perfect for a sweet finish.", 8.99, com.example.littlelemon.R.drawable.lemondessert, "desserts"),
    Dish(3, "Grilled Fish", "Freshly grilled fish served with a side of seasonal vegetables.", 15.99, com.example.littlelemon.R.drawable.grilledfish, "mains"),
    Dish(4, "Pasta", "Classic pasta dish with a rich tomato sauce and herbs.", 10.99, com.example.littlelemon.R.drawable.pasta, "mains"),
    Dish(5, "Bruschetta", "Oven-baked bruschetta topped with tomatoes, basil, and a drizzle of balsamic glaze.", 6.99, com.example.littlelemon.R.drawable.bruschetta, "starters")
)