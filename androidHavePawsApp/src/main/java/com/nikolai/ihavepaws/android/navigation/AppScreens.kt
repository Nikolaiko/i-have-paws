package com.nikolai.ihavepaws.android.navigation

import com.nikolai.ihavepaws.android.model.consts.groupIdParameter
import com.nikolai.ihavepaws.android.model.consts.groupScreenRoute
import com.nikolai.ihavepaws.android.model.consts.mainScreenRoute
import com.nikolai.ihavepaws.android.model.consts.popBackRoute

sealed class AppScreens(
    var route: String
) {
    object MainScreen: AppScreens(mainScreenRoute)
    object PopBackScreen: AppScreens(popBackRoute)

    object GroupScreen: AppScreens("$groupScreenRoute/{$groupIdParameter}") {
        fun buildRoute(groupIdValue: String) {
            route = "$groupScreenRoute/$groupIdValue"
        }
    }
}
