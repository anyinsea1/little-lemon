package com.example.littlelemon


import android.content.Context
import android.content.SharedPreferences
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

// These should be in Destinations.kt, but are included here for context
const val PREF_FILE_KEY = "LittleLemonPrefs"
const val KEY_IS_ONBOARDING_COMPLETE = "isOnboardingComplete"

@Composable
fun MyNavigation(
    navController: NavHostController,
    menuItems: List<MenuItemEntity>,
    onSearchQueryChange: () -> Unit,
    searchQuery: String
) {
    val context = LocalContext.current
    val sharedPrefs: SharedPreferences =
        context.getSharedPreferences(PREF_FILE_KEY, Context.MODE_PRIVATE)
    val isOnboardingComplete = sharedPrefs.getBoolean(KEY_IS_ONBOARDING_COMPLETE, false)

    val startDestination = if (isOnboardingComplete) {
        Home.route
    } else {
        Onboarding.route
    }

    // State for search query is hoisted here
    var searchQuery by remember { mutableStateOf("") }

    // Filter the menu items based on the search query
    val filteredMenuItems = if (searchQuery.isNotBlank()) {
        menuItems.filter { it.title.contains(searchQuery, ignoreCase = true) }
    } else {
        menuItems
    }

    NavHost(
        navController = navController,
        startDestination = startDestination
    ) {
        composable(Onboarding.route) {
            Onboarding(
                onRegister = {
                    sharedPrefs.edit()
                        .putBoolean(KEY_IS_ONBOARDING_COMPLETE, true)
                        .apply()
                    navController.navigate(Home.route) {
                        popUpTo(Onboarding.route) { inclusive = true }
                    }
                }
            )
        }

        composable(Home.route) {
            Home(
                onNavigateToProfile = { navController.navigate(Profile.route) },
                menuItems = filteredMenuItems,
                searchQuery = searchQuery,
                onSearchQueryChange = { newQuery ->
                    searchQuery = newQuery
                }
            )
        }

        composable(Profile.route) {
            Profile(
                onLogout = {
                    sharedPrefs.edit()
                        .putBoolean(KEY_IS_ONBOARDING_COMPLETE, false)
                        .apply()
                    navController.navigate(Onboarding.route) {
                        popUpTo(Home.route) { inclusive = true }
                    }
                }
            )
        }
    }
}