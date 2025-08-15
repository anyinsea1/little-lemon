package com.example.littlelemon

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.compose.ui.tooling.preview.Preview
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage

@Composable
fun MenuItems(menuItems: List<MenuItemEntity>) {
    LazyColumn(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        items(menuItems) { item ->
            MenuItemView(item)
            Spacer(modifier = Modifier.height(8.dp))
        }
    }
}

@Preview
@Composable
fun MenuItemsPreview() {
    val menuItems = listOf(
        MenuItemEntity(
            id = 1,
            title = "Greek Salad",
            description = "The famous Greek salad of crispy lettuce, peppers, olives, and our Chicago style feta cheese, garnished with crunchy garlic and rosemary croutons.",
            price = 12.99,
            image = "https://github.com/Meta-Mobile-Developer-PC/Working-With-Data-API/blob/main/images/greekSalad.jpg?raw=true",
            category = "starters"
        )
    )
    MaterialTheme {
        MenuItems(menuItems = menuItems)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Hero(
    searchQuery: String,
    onSearchQueryChange: (String) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color(0xFF495E57))
            .padding(vertical = 12.dp, horizontal = 8.dp)
    ) {
        Spacer(modifier = Modifier.height(10.dp))
        TextField(
            value = searchQuery,
            onValueChange = onSearchQueryChange,
            modifier = Modifier.fillMaxWidth(),
            label = { Text("Enter search phrase") },
            colors = TextFieldDefaults.colors(
                focusedContainerColor = Color.White,
                unfocusedContainerColor = Color.White,
                focusedTextColor = Color.Black,
                unfocusedTextColor = Color.Black,
                cursorColor = Color.Black,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent
            )
        )
    }
}

@Preview
@Composable
fun HeroPreview() {
    MaterialTheme {
        Hero(searchQuery = "Search", onSearchQueryChange = {})
    }
}


@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun MenuItemView(item: MenuItemEntity) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .background(Color(0xFFF7F7F7))
    ) {
        Column(
            modifier = Modifier.weight(1f)
        ) {
            Text(
                text = item.title,
                style = MaterialTheme.typography.titleLarge
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = "$${item.price}",
                style = MaterialTheme.typography.bodyLarge,
                color = Color(0xFF495E57)
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = item.description,
                style = MaterialTheme.typography.bodyMedium
            )
        }
        Spacer(modifier = Modifier.width(16.dp))
        GlideImage(
            model = item.image,
            contentDescription = "Image of ${item.title}",
            modifier = Modifier.size(90.dp),
            contentScale = ContentScale.Crop
        )
    }
}

@Preview
@Composable
fun MenuItemViewPreview() {
    val menuItem = MenuItemEntity(
        id = 1,
        title = "Greek Salad",
        description = "The famous Greek salad of crispy lettuce, peppers, olives, and our Chicago style feta cheese, garnished with crunchy garlic and rosemary croutons.",
        price = 12.99,
        image = "https://github.com/Meta-Mobile-Developer-PC/Working-With-Data-API/blob/main/images/greekSalad.jpg?raw=true",
        category = "starters"
    )
    MaterialTheme {
        MenuItemView(item = menuItem)
    }
}
