package com.example.littlelemon

interface Destination {
    val route: String
}

object Onboarding : Destination {
    override val route = "onboarding"
}

object Home : Destination {
    override val route = "home"
}

object Profile : Destination {
    override val route = "profile"
}