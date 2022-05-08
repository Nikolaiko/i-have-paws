package com.nikolai.ihavepaws.android.navigation

import androidx.navigation.NavHostController
import com.nikolai.ihavepaws.android.model.consts.popBackRoute

class NavComponentNavigator : AppNavigator {
    private var controller: NavHostController? = null

    override fun setNavigator(navObject: NavHostController) {
        controller = navObject
    }

    override fun navigateTo(destination: AppScreens) {
        when(destination.route) {
            popBackRoute -> controller?.popBackStack()
            else -> controller?.navigate(destination.route)
        }
    }
}
