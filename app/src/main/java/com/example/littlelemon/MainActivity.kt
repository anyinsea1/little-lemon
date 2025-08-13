package com.example.littlelemon

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
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
                // Fetch from DB to check if empty
                val itemsFromDb = database.menuItemDao().getAllMenuItems().firstOrNull() ?: emptyList()

                if (itemsFromDb.isEmpty()) {
                    // Step 2: Fetch from server, decode with Ktor and kotlinx.serialization
                    val menuResponse: MenuNetwork = httpClient.get(MENU_API_URL).body()
                    val menuItems: List<MenuItemNetwork> = menuResponse.menu

                    // Convert to Room entities
                    val menuEntities = menuItems.map { it.toMenuItemRoom() }
                    // Store in DB
                    database.menuItemDao().insertAll(menuEntities)
                }
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
                    //Retrieve Items From the Database as State in Your MainActivity
                    val menuItems by database.menuItemDao().getAllMenuItems().collectAsState(initial = emptyList())


                    // Create a NavController for navigation
                    val navController = rememberNavController()

                    var searchQuery by remember { mutableStateOf("") }

                    MyNavigation(
                        navController = navController,
                        menuItems = menuItems,
                        searchQuery = searchQuery,
                        onSearchQueryChange = { newQuery -> searchQuery = newQuery }
                    )
                }
            }
        }
    }
}