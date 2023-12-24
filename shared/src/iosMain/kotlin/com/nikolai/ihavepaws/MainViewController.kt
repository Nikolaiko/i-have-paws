package com.nikolai.ihavepaws

import androidx.compose.ui.window.ComposeUIViewController
import com.nikolai.ihavepaws.AppMainView
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