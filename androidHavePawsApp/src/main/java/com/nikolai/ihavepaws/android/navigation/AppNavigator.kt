package com.nikolai.ihavepaws.android.navigation

import androidx.navigation.NavHostController

interface AppNavigator {
    fun setNavigator(navObject: NavHostController)
    fun navigateTo(destination: AppScreens)
}