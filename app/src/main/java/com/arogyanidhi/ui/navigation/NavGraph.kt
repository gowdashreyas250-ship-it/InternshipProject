package com.arogyanidhi.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.arogyanidhi.ui.screens.ChatbotScreen
import com.arogyanidhi.ui.screens.EligibilityScreen
import com.arogyanidhi.ui.screens.EmergencyScreen
import com.arogyanidhi.ui.screens.FirstAidScreen
import com.arogyanidhi.ui.screens.HealthTipsScreen
import com.arogyanidhi.ui.screens.HomeScreen
import com.arogyanidhi.ui.screens.HospitalSearchScreen
import com.arogyanidhi.ui.screens.LoginScreen
import com.arogyanidhi.ui.screens.ProfileScreen
import com.arogyanidhi.ui.screens.SchemesListScreen
import com.arogyanidhi.ui.screens.WelcomeScreen

@Composable
fun NavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = Screen.Welcome.route
    ) {
        composable(Screen.Welcome.route) {
            WelcomeScreen(navController)
        }
        composable(Screen.Login.route) {
            LoginScreen(navController)
        }
        composable(Screen.Home.route) {
            HomeScreen(navController)
        }
        composable(Screen.Schemes.route) {
            SchemesListScreen(navController)
        }
        composable(Screen.Eligibility.route) {
            EligibilityScreen(navController)
        }
        composable(Screen.Hospitals.route) {
            HospitalSearchScreen(navController)
        }
        composable(Screen.Chatbot.route) {
            ChatbotScreen(navController)
        }
        composable(Screen.Emergency.route) {
            EmergencyScreen(navController)
        }
        composable(Screen.FirstAid.route) {
            FirstAidScreen(navController)
        }
        composable(Screen.HealthTips.route) {
            HealthTipsScreen(navController)
        }
        composable(Screen.Profile.route) {
            ProfileScreen(navController)
        }
    }
}
