package com.nikolai.ihavepaws.navigation

import androidx.compose.animation.AnimatedVisibility
import com.nikolai.ihavepaws.navigation.enums.NavigationType
import com.nikolai.ihavepaws.navigation.enums.PopDestination
import com.nikolai.ihavepaws.navigation.model.ComposableView
import com.nikolai.ihavepaws.navigation.model.Screen
import com.nikolai.ihavepaws.navigation.model.ScreenStack

class NavigationController {
    var navigationType = NavigationType.Push
    var currentScreen: Screen? = null

    private var screenStack = ScreenStack()

    fun pushScreen(view: ComposableView) {
        navigationType = NavigationType.Push
        screenStack.push(
            Screen(
                id = "",
                view = view
            )
        )
    }

    fun pop(destination: PopDestination = PopDestination.Prev) {
        navigationType = NavigationType.Pop
        when(destination) {
            PopDestination.Prev -> screenStack.popToPrevious()
            PopDestination.Root -> screenStack.popToRoot()
        }
    }
}