package com.nikolai.ihavepaws.views

import androidx.compose.ui.interop.LocalUIViewController
import androidx.compose.ui.window.ComposeUIViewController
import com.nikolai.ihavepaws.AppMainView
import com.nikolai.ihavepaws.groupsScreen.view.GroupsScreen
import platform.UIKit.UIScreen
import platform.UIKit.UIUserInterfaceStyle

fun MainViewController() = ComposeUIViewController {
    val isDarkTheme =
        UIScreen.mainScreen.traitCollection.userInterfaceStyle ==
                UIUserInterfaceStyle.UIUserInterfaceStyleDark
    AppMainView(
        darkTheme = isDarkTheme,
        dynamicColor = false
    )
}