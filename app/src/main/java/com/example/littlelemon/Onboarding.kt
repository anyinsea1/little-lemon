package com.example.littlelemon


import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField // Often preferred for input fields
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.tooling.preview.Preview


@Composable
fun Onboarding(onRegister: () -> Unit) {
    var firstName by remember { mutableStateOf("") }
    var lastName by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }

    Column(
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.Start,
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White) // Overall screen background
    ) {
        // --- Header Section (Step 2 & New: Image) ---
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color(0xC3FFFFFF)) // Dark green header background
                .padding(vertical = 16.dp)
        ) {
            Image(
                painter = painterResource(id = R.drawable.logo), // Assumes logo.png in res/drawable
                contentDescription = "Little Lemon Logo",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp) // Adjust height as needed
                    .padding(horizontal = 8.dp),
                contentScale = ContentScale.Fit // Adjust content scale as needed
            )
        }

        // --- Prompt Text Section (Step 4) ---
        Text(
            text = "Let's get to know you",
            modifier = Modifier
                .fillMaxWidth()
                .background(Color(0xFF495E57)) // Same background as header for continuity
                .padding(vertical = 40.dp), // More padding for this section
            textAlign = TextAlign.Center,
            color = Color(0xFFEDEFEE), // Light grey text color
            fontSize = 24.sp,
            style = MaterialTheme.typography.headlineSmall
        )

        // --- User Input Section (Step 4) ---
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 32.dp)
        ) {
            Text(
                text = "Personal information",
                modifier = Modifier.padding(bottom = 16.dp),
                fontSize = 18.sp,
                color = Color(0xFF333333) // Dark grey for labels
            )

            OutlinedTextField(
                value = firstName,
                onValueChange = { firstName = it },
                label = { Text("First Name") },
                modifier = Modifier.fillMaxWidth(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text)
            )
            Spacer(modifier = Modifier.height(16.dp)) // Spacing between fields

            OutlinedTextField(
                value = lastName,
                onValueChange = { lastName = it },
                label = { Text("Last Name") },
                modifier = Modifier.fillMaxWidth(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text)
            )
            Spacer(modifier = Modifier.height(16.dp))

            OutlinedTextField(
                value = email,
                onValueChange = { email = it },
                label = { Text("Email") },
                modifier = Modifier.fillMaxWidth(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email) // Specific keyboard for email
            )
        }

        // --- Register Button (Step 5) ---
        // Pushing the button to the bottom using Spacer with weight
        Spacer(modifier = Modifier.weight(1f)) // This pushes the button to the bottom

        Button(
            onClick = {
                // Check if all fields are non-empty
                if (firstName.isNotBlank() && lastName.isNotBlank() && email.isNotBlank()) {
                    // Call the navigation function passed from MyNavigation.kt
                    onRegister()
                }
                println("Register button clicked!")
                println("First Name: $firstName, Last Name: $lastName, Email: $email")
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 24.dp), // Padding for the button
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFF4CE14)), // Yellow button
            shape = RoundedCornerShape(8.dp) // Slightly rounded corners
        ) {
            Text(
                text = "Register",
                color = Color(0xFF333333), // Dark text on yellow button
                fontSize = 16.sp,
                modifier = Modifier.padding(vertical = 4.dp)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewOnboarding() {
    Onboarding(
        // Provide an empty lambda function for the onRegister parameter
        onRegister = {}
    )
}


