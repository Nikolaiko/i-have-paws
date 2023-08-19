@file:Suppress("INLINE_FROM_HIGHER_PLATFORM")
package com.nikolai.ihavepaws

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import com.nikolai.ihavepaws.di.components.KoinStorage
import com.nikolai.ihavepaws.di.groupsScreenViewModel
import com.nikolai.ihavepaws.groupsScreen.contract.GroupsScreen
import com.nikolai.ihavepaws.groupsScreen.view.GroupsView
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
        val viewModel = getViewModel(
            key = groupsScreenViewModel,
            factory = viewModelFactory {
                GroupsViewModel(storage = KoinStorage().observableLocalStorage)
            }
        )
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
