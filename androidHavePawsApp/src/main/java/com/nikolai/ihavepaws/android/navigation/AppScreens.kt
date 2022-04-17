package com.nikolai.ihavepaws.android.navigation

sealed class AppScreens(
    val route: String
) {
    object MainScreen: AppScreens(mainScreenRoute)
}
