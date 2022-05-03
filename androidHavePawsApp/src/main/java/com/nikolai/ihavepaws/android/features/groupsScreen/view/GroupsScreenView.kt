package com.nikolai.ihavepaws.android.features.groupsScreen.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.MutableLiveData
import com.nikolai.ihavepaws.android.R
import com.nikolai.ihavepaws.android.commonComposables.topPanel.TopAddPanel
import com.nikolai.ihavepaws.android.commonComposables.topPanel.TopTitlePanel
import com.nikolai.ihavepaws.android.features.addGroupScreen.view.AddGroupScreen
import com.nikolai.ihavepaws.android.features.groupsScreen.model.GroupsViewState
import com.nikolai.ihavepaws.android.model.consts.initialEffectName
import com.nikolai.ihavepaws.android.model.consts.topAddHeight
import com.nikolai.ihavepaws.android.model.consts.topAddTopPadding
import com.nikolai.ihavepaws.android.model.style.rootBackgroundColor
import com.nikolai.ihavepaws.android.model.typealiases.VoidCallback
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun GroupsScreenView(
    state: GroupsViewState,
    initGroupsCallback: VoidCallback,
    showAddGroupCallback: VoidCallback,
    hideAddGroupCallback: VoidCallback
) {
    val groupsList = state.groups.observeAsState(emptyList())
    val showAddGroup = state.showAddGroupScreen.observeAsState(false)

    LaunchedEffect(initialEffectName) {
        initGroupsCallback()
    }

    BoxWithConstraints(
        modifier = Modifier
            .fillMaxSize()
            .background(rootBackgroundColor)
    ) {
        val maxViewHeight = maxHeight
        val maxViewWidth = maxWidth

        Column(
            modifier = Modifier.
            fillMaxSize()
        ) {
            TopAddPanel(
                modifier = Modifier
                    .height(maxViewHeight.times(topAddHeight))
                    .padding(top = maxViewHeight.times(topAddTopPadding)),
                tapCallback = showAddGroupCallback
            )
            TopTitlePanel(
                title = stringResource(id = R.string.groups_title_text)
            )
            val columnState = rememberLazyListState()
            LazyColumn(
                state = columnState
            ) {
                items(items = groupsList.value, itemContent = { item ->
                    Text(item.name)
                })
            }
        }
        if (showAddGroup.value) {
            AddGroupScreen(
                modifier = Modifier
                    .width(maxViewWidth.times(0.9f))
                    .height(maxViewHeight.times(0.5f)),
                onSuccess = {
                    initGroupsCallback()
                    hideAddGroupCallback()
                },
                onCloseRequest = hideAddGroupCallback
            )
        }
    }
}

@OptIn(ExperimentalMaterialApi::class)
fun openAddGroupScreen(
    scope: CoroutineScope,
    state: BottomSheetScaffoldState
) {
    scope.launch {
        state.bottomSheetState.expand()
    }
}

@Preview
@Composable
fun MainScreenPreview() {
    Surface {
        GroupsScreenView(
            state = GroupsViewState(
                groups = MutableLiveData(emptyList()),
                showAddGroupScreen = MutableLiveData(true)
            ),
            initGroupsCallback = { },
            showAddGroupCallback = { },
            hideAddGroupCallback = { }
        )
    }
}