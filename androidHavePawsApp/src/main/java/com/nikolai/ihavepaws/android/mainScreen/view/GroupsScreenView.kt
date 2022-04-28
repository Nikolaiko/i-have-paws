package com.nikolai.ihavepaws.android.mainScreen.view

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.remember
import com.nikolai.ihavepaws.android.mainScreen.model.GroupsViewState
import com.nikolai.ihavepaws.android.model.consts.initialEffectName
import com.nikolai.ihavepaws.android.model.typealiases.VoidCallback

@Composable
fun GroupsScreenView(
    state: GroupsViewState,
    initGroupsCallback: VoidCallback
) {

    val groupsList = state.groups.observeAsState(emptyList())

    LaunchedEffect(initialEffectName) {
        initGroupsCallback()
    }

    val columnState = rememberLazyListState()
    LazyColumn(
        state = columnState
    ) {
        items(items = groupsList.value, itemContent = { item ->
            Text(item.name)
        })
    }
}