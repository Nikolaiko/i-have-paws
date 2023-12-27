@file:Suppress("INLINE_FROM_HIGHER_PLATFORM")
package com.nikolai.ihavepaws.appScreens.views

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import com.nikolai.ihavepaws.appScreens.viewModels.AppViewModel
import com.nikolai.ihavepaws.groupsScreen.view.GroupsView
import com.nikolai.ihavepaws.groupsScreen.viewModel.GroupsViewModel
import org.koin.compose.koinInject

@Composable
fun AppMainView() {
    val appViewModel: AppViewModel = koinInject()
    val appState = appViewModel.state.collectAsState()

    AppCommonTheme(
        darkTheme = appState.value.isDarkMode,
        dynamicColor = appState.value.isTrueColor
    ) {
        val viewModel = koinInject<GroupsViewModel>()
        val state by viewModel.state.collectAsState()

        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            GroupsView(
                state = state,
                onAddGroup = viewModel::onEvent
            )
        }
    }
}
