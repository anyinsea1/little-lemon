package com.example.littlelemon


import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import androidx.room.Room
import androidx.lifecycle.lifecycleScope
import android.util.Log
import com.example.littlelemon.ui.theme.LittleLemonTheme
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.engine.android.Android
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.get
import io.ktor.serialization.kotlinx.json.json
import kotlinx.coroutines.launch
import kotlinx.serialization.json.Json
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import kotlinx.coroutines.flow.firstOrNull


const val MENU_API_URL = "https://raw.githubusercontent.com/Meta-Mobile-Developer-PC/Working-With-Data-API/main/menu.json"

class MainActivity : ComponentActivity() {

    private val httpClient = HttpClient(Android) {
        install(ContentNegotiation) {
            json(Json {
                ignoreUnknownKeys = true
                isLenient = true
                prettyPrint = true
            })
        }
    }

    private lateinit var database: AppDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        database = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java,
            "little_lemon_database"
        ).build()

        lifecycleScope.launch {
            try {
                // CORRECTED: Collect the Flow to get the first list from the database
                val itemsFromDb = database.menuItemDao().getAllMenuItems().firstOrNull() ?: emptyList()

                // Check if the list is empty before fetching from the network
                if (itemsFromDb.isEmpty()) {
                    Log.d("MenuFetch", "Fetching menu from server...")
                    val menuResponse: MenuNetwork = httpClient.get(MENU_API_URL).body()
                    Log.d("MenuFetch", "Menu fetched successfully.")

                    val menuEntities = menuResponse.menu.map { networkItem ->
                        MenuItemEntity(
                            id = networkItem.id,
                            title = networkItem.title,
                            description = networkItem.description,
                            price = networkItem.price.toDouble(), // <-- CORRECTED: Convert price to Double
                            image = networkItem.image,
                            category = networkItem.category
                        )
                    }
                    database.menuItemDao().insertAll(menuEntities)
                }

                // Log the size of the final list
                Log.d("MenuFetch", "Total items in DB: ${itemsFromDb.size}")
            } catch (e: Exception) {
                Log.e("MenuFetch", "Error fetching or saving menu: ${e.message}", e)
            }
        }

        setContent {
            LittleLemonTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val menuItems by database.menuItemDao().getAllMenuItems().collectAsState(initial = emptyList())
                    val navController = rememberNavController()

                    // Correctly declare the search query state
                    var searchQuery by remember { mutableStateOf("") }

                    // Filter menu items based on the search query
                    val filteredMenuItems = menuItems.filter {
                        it.title.contains(searchQuery, ignoreCase = true)
                    }

                    // Call MyNavigation with all the required arguments
                    MyNavigation(
                        navController = navController,
                        menuItems = filteredMenuItems,
                        searchQuery = searchQuery,
                        onSearchQueryChange = {
                            val it = ""
                            searchQuery = it
                        }
                    )
                }
            }
        }
    }
}