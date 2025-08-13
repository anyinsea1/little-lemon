package com.example.littlelemon

import android.content.Context
import android.content.SharedPreferences
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

const val PREF_FILE_KEY = "LittleLemonPrefs"
const val KEY_IS_ONBOARDING_COMPLETE = "isOnboardingComplete"

@Composable
fun MyNavigation(
    navController: NavHostController,
    menuItems: List<MenuItemEntity>,
    searchQuery: String,
    onSearchQueryChange: (String) -> Unit
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
                onSearchQueryChange = onSearchQueryChange
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