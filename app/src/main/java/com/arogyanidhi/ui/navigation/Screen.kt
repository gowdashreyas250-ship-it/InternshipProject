package com.arogyanidhi.ui.navigation

sealed class Screen(val route: String) {
    object Welcome : Screen("welcome")
    object Login : Screen("login")
    object Home : Screen("home")
    object Schemes : Screen("schemes")
    object Eligibility : Screen("eligibility")
    object Hospitals : Screen("hospitals")
    object Chatbot : Screen("chatbot")
    object Emergency : Screen("emergency")
    object FirstAid : Screen("first_aid")
    object HealthTips : Screen("health_tips")
    object Profile : Screen("profile")
}
