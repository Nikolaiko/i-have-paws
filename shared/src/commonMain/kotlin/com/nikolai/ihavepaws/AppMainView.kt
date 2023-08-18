@file:Suppress("INLINE_FROM_HIGHER_PLATFORM")
package com.nikolai.ihavepaws

import androidx.compose.runtime.Composable
import com.nikolai.ihavepaws.di.components.KoinStorage
import com.nikolai.ihavepaws.di.groupsScreenViewModel
import com.nikolai.ihavepaws.groupsScreen.view.GroupsScreen
import com.nikolai.ihavepaws.groupsScreen.viewModel.GroupsViewModel
import com.nikolai.ihavepaws.views.AppCommonTheme
import dev.icerock.moko.mvvm.compose.getViewModel
import dev.icerock.moko.mvvm.compose.viewModelFactory

@Composable
fun AppMainView(
    darkTheme: Boolean,
    dynamicColor: Boolean
) {
    AppCommonTheme(
        darkTheme = darkTheme,
        dynamicColor = dynamicColor
    ) {
        GroupsScreen(
            viewModel = getViewModel(
                key = groupsScreenViewModel,
                factory = viewModelFactory {
                    GroupsViewModel(storage = KoinStorage().observableLocalStorage)
                }
            )
        )
    }
}
