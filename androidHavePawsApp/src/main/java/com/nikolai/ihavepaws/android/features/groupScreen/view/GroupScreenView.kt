package com.nikolai.ihavepaws.android.features.groupScreen.view

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.MutableLiveData
import com.nikolai.ihavepaws.android.R
import com.nikolai.ihavepaws.android.commonComposables.topPanel.TopAddPanel
import com.nikolai.ihavepaws.android.commonComposables.topPanel.TopTitleWithArrowPanel
import com.nikolai.ihavepaws.android.features.addGroupItemScreen.view.AddGroupItemScreen
import com.nikolai.ihavepaws.android.features.addGroupScreen.view.AddGroupScreen
import com.nikolai.ihavepaws.android.features.groupScreen.model.GroupScreenState
import com.nikolai.ihavepaws.android.model.consts.*
import com.nikolai.ihavepaws.android.model.style.lightBluePrimaryColor
import com.nikolai.ihavepaws.android.model.style.rootBackgroundColor
import com.nikolai.ihavepaws.android.model.typealiases.GroupItemActionCallback
import com.nikolai.ihavepaws.android.model.typealiases.StringCallback
import com.nikolai.ihavepaws.android.model.typealiases.VoidCallback
import kotlinx.coroutines.flow.MutableSharedFlow

@Composable
fun GroupScreenView(
    state: GroupScreenState,
    groupName: String,
    initGroupCallback: StringCallback,
    toggleStateCallback: GroupItemActionCallback,
    deleteGroupItemCallback: GroupItemActionCallback,
    backButtonTapCallback: VoidCallback,
    showAddGroupItemCallback: VoidCallback,
    hideAddGroupItemCallback: VoidCallback
) {
    val groupItems = state.groupItems.observeAsState(emptyList())
    val showAddGroupItem = state.addItemMenuShow.observeAsState(false)
    val selectedGroupId = state.selectedGroupId.observeAsState("")

    LaunchedEffect(initialEffectName) {
        initGroupCallback(groupName)
    }

    val context = LocalContext.current.applicationContext
    LaunchedEffect(errorEffectName) {
        state.messages.collect {
            Toast.makeText(context, it.message, Toast.LENGTH_LONG).show()
        }
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
                tapCallback = showAddGroupItemCallback
            )
            TopTitleWithArrowPanel(
                title = groupName,
                backTapCallback = backButtonTapCallback
            )
            val columnState = rememberLazyListState()
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                state = columnState,
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(maxViewHeight.times(groupVertSpacing))
            ) {
                items(
                    items = groupItems.value, itemContent = { item ->
                        GroupItemRow(
                            title = item.title,
                            isActive = item.active,
                            toggleState = { toggleStateCallback(item) },
                            crossTapCallback = { deleteGroupItemCallback(item) },
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(maxViewHeight.times(groupHeightCoff))
                        )
                    })
            }
        }
        Column(
            modifier = Modifier
                .fillMaxSize(),
            verticalArrangement = Arrangement.Bottom,
            horizontalAlignment = Alignment.End
        ) {
            Image(
                painter = rememberVectorPainter(image = Icons.Filled.Add),
                contentDescription = stringResource(id = R.string.add_button_description),
                contentScale = ContentScale.FillHeight,
                colorFilter = ColorFilter.tint(lightBluePrimaryColor),
                modifier = Modifier
                    .height(maxViewHeight.times(0.2f))
                    .width(maxViewHeight.times(0.2f))
                    .clickable {  }
            )
        }
        if (showAddGroupItem.value) {
            AddGroupItemScreen(
                modifier = Modifier
                    .width(maxViewWidth.times(0.9f))
                    .height(maxViewHeight.times(0.5f)),
                selectedGroupId = selectedGroupId.value,
                onSuccess = {
                    initGroupCallback(groupName)
                    hideAddGroupItemCallback()
                },
                onCloseRequest = hideAddGroupItemCallback
            )
        }
    }
}

@Preview
@Composable
fun GroupScreenViewPreview() {
    Surface{
        GroupScreenView(
            state = GroupScreenState(
                randomButtonEnabled = MutableLiveData(false),
                addItemMenuShow = MutableLiveData(false),
                messages = MutableSharedFlow(),
                groupItems = MutableLiveData(emptyList()),
                selectedGroupId = MutableLiveData("")
            ),
            "name",
            initGroupCallback =  { },
            showAddGroupItemCallback = { },
            hideAddGroupItemCallback = { },
            backButtonTapCallback = { },
            toggleStateCallback = { },
            deleteGroupItemCallback = { }
        )
    }
}