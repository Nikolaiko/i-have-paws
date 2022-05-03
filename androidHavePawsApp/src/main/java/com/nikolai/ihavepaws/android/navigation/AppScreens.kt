package com.nikolai.ihavepaws.android.navigation

import com.nikolai.ihavepaws.android.model.consts.mainScreenRoute

sealed class AppScreens(
    val route: String
) {
    object MainScreen: AppScreens(mainScreenRoute)
}
