package com.example.littlelemon



import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.compose.ui.text.font.FontWeight
import com.example.littlelemon.data.Categories
import com.example.littlelemon.data.Dish
import com.example.littlelemon.data.dishes


@Composable
fun MenuScreen() {
    Column {
        LazyRow {
            items(Categories) { category ->
                MenuCategory(category)
            }
        }
        HorizontalDivider(
            modifier = Modifier
                .padding(8.dp), thickness = 1.dp, color = Color.Gray
        )
        LazyColumn {
            items(dishes) { dish ->
                MenuDish(dish)
            }
        }
    }
}

@Composable
fun MenuCategory(category: String) {
    Button(
        onClick = { /* Handle click */ },
        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFD3D3D3)),
        shape = RoundedCornerShape(40),
        modifier = Modifier.padding(1.dp)

    ) {
        Text(
            text = category,
            color = Color.Black,
        )
    }
}

@Preview
@Composable
fun MenuCategoryPreview() {
    MenuCategory("lunch")
}

@Composable
fun MenuDish(dish: Dish) {
    Card {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        ){
            Column {
                Text(
                    text = dish.name, fontSize = 18.sp, fontWeight = FontWeight.Bold
                )
                Text(
                    text = dish.description,
                    color = Color.Gray,
                    modifier = Modifier
                        .padding(top = 5.dp, bottom = 5.dp)
                        .fillMaxWidth(.75f)
                )
                Text(
                    text = "$${dish.price}", color = Color.Gray, fontWeight = FontWeight.Bold
                )
            }
            Image(
                painter = painterResource(id = dish.imageResId),
                contentDescription = dish.name,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .padding(start = 8.dp)
                    .clip(RoundedCornerShape(8.dp))
                    .width(80.dp)
                    .height(80.dp)
            )
        }
    }
    Spacer(modifier = Modifier.height(2.dp))
}

@Preview
@Composable
fun MenuDishPreview() {
    MenuDish(
        Dish(
            id = 1,
            name = "Greek Salad",
            description = "The famous greek salad of " +
                    "crispy lettuce, peppers, olives," +
                    "our Chicago,,.",
            price = 12.99,
            imageResId = com.example.littlelemon.R.drawable.greeksalad,
            category = "starters"
        )
    )
}