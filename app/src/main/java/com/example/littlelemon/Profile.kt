
package com.example.littlelemon

import android.content.Context
import android.content.SharedPreferences
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

// Reusing constants for SharedPreferences keys
const val KEY_FIRST_NAME = "firstName"
const val KEY_LAST_NAME = "lastName"
const val KEY_EMAIL = "email"


@Composable
fun Profile(onLogout: () -> Unit) { // Profile now accepts the onLogout callback
    val context = LocalContext.current
    val sharedPrefs: SharedPreferences = context.getSharedPreferences(PREF_FILE_KEY, Context.MODE_PRIVATE)

    val firstName = sharedPrefs.getString(KEY_FIRST_NAME, "") ?: ""
    val lastName = sharedPrefs.getString(KEY_LAST_NAME, "") ?: ""
    val email = sharedPrefs.getString(KEY_EMAIL, "") ?: ""

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White) // Overall screen background
    ) {
        // Header Section
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color(0xC3FFFFFF))
                .padding(vertical = 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(id = R.drawable.logo),
                contentDescription = "Little Lemon Logo",
                modifier = Modifier
                    .fillMaxWidth(0.6f)
                    .height(50.dp)
                    .padding(horizontal = 8.dp),
                contentScale = ContentScale.Fit
            )
        }

        // Profile Information Section
        Text(
            text = "Profile information:",
            modifier = Modifier.padding(start = 16.dp, top = 32.dp, bottom = 16.dp),
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF333333)
        )

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
        ) {
            Text(
                text = "First name: $firstName",
                modifier = Modifier.padding(vertical = 8.dp),
                fontSize = 16.sp,
                color = Color(0xFF495E57)
            )
            Text(
                text = "Last name: $lastName",
                modifier = Modifier.padding(vertical = 8.dp),
                fontSize = 16.sp,
                color = Color(0xFF495E57)
            )
            Text(
                text = "Email: $email",
                modifier = Modifier.padding(vertical = 8.dp),
                fontSize = 16.sp,
                color = Color(0xFF495E57)
            )
        }

        Spacer(modifier = Modifier.weight(1f)) // Pushes the button to the bottom

        // Add the Logout Button
        Button(
            onClick = {
                // Clear all relevant data from shared preferences
                sharedPrefs.edit().apply {
                    remove(KEY_FIRST_NAME)
                    remove(KEY_LAST_NAME)
                    remove(KEY_EMAIL)
                    putBoolean(KEY_IS_ONBOARDING_COMPLETE, false) // Mark onboarding as incomplete
                    apply() // Asynchronously save changes
                }
                onLogout() // Trigger navigation back to Onboarding
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 24.dp), // Padding for the button
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFF4CE14)), // Yellow button
            shape = RoundedCornerShape(8.dp) // Slightly rounded corners
        ) {
            Text(
                text = "Log out",
                color = Color(0xFF333333), // Dark text on yellow button
                fontSize = 16.sp,
                modifier = Modifier.padding(vertical = 4.dp)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewProfile() {
    // For preview, provide an empty lambda for onLogout
    Profile(onLogout = {})
}