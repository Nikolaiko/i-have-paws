package com.nikolai.ihavepaws

import androidx.compose.runtime.Composable
import com.nikolai.ihavepaws.groupsScreen.view.GroupsScreen
import com.nikolai.ihavepaws.views.AppCommonTheme

@Composable
fun AppMainView(
    darkTheme: Boolean,
    dynamicColor: Boolean
) {
    AppCommonTheme(
        darkTheme = darkTheme,
        dynamicColor = dynamicColor
    ) {
        GroupsScreen()
    }
}
