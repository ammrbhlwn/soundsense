package com.example.soundsense.ui.navigation

sealed class Screen(val route: String) {
    data object Home : Screen("home")
    data object Microphone : Screen("microphone")
}
