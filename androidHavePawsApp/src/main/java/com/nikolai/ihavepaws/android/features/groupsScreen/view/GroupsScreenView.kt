package com.nikolai.ihavepaws.android.features.groupsScreen.view

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.MutableLiveData
import com.nikolai.ihavepaws.android.R
import com.nikolai.ihavepaws.android.commonComposables.topPanel.TopAddPanel
import com.nikolai.ihavepaws.android.commonComposables.topPanel.TopTitlePanel
import com.nikolai.ihavepaws.android.features.addGroupScreen.view.AddGroupScreen
import com.nikolai.ihavepaws.android.features.groupsScreen.model.GroupsViewState
import com.nikolai.ihavepaws.android.model.ViewModelMessage
import com.nikolai.ihavepaws.android.model.consts.*
import com.nikolai.ihavepaws.android.model.style.rootBackgroundColor
import com.nikolai.ihavepaws.android.model.typealiases.GroupActionCallback
import com.nikolai.ihavepaws.android.model.typealiases.VoidCallback
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun GroupsScreenView(
    state: GroupsViewState,
    initGroupsCallback: VoidCallback,
    showAddGroupCallback: VoidCallback,
    hideAddGroupCallback: VoidCallback,
    deleteGroupCallback: GroupActionCallback,
    openGroupCallback: GroupActionCallback
) {
    val groupsList = state.groups.observeAsState(emptyList())
    val showAddGroup = state.showAddGroupScreen.observeAsState(false)

    val context = LocalContext.current.applicationContext
    LaunchedEffect(errorEffectName) {
        state.messages.collect {
            Toast.makeText(context, it.message, Toast.LENGTH_LONG).show()
        }
    }

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
                modifier = Modifier.fillMaxSize(),
                state = columnState,
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(maxViewHeight.times(groupVertSpacing))
            ) {
                items(
                    items = groupsList.value, itemContent = { item ->
                    GroupRow(
                        modifier = Modifier
                            .width(maxViewWidth.times(groupWidthCoff))
                            .height(maxViewHeight.times(groupHeightCoff))
                            .clip(RoundedCornerShape(maxViewHeight.times(groupCornerRadius)))
                            .clickable { openGroupCallback(item) },
                        title = item.name,
                        crossTapCallback = { deleteGroupCallback(item) }
                    )
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

@Preview
@Composable
fun MainScreenPreview() {
    Surface {
        GroupsScreenView(
            state = GroupsViewState(
                groups = MutableLiveData(emptyList()),
                showAddGroupScreen = MutableLiveData(true),
                messages = MutableSharedFlow<ViewModelMessage>()
            ),
            initGroupsCallback = { },
            showAddGroupCallback = { },
            hideAddGroupCallback = { },
            deleteGroupCallback =  { },
            openGroupCallback = { }
        )
    }
}