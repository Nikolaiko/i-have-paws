package com.nikolai.ihavepaws.android.features.groupScreen.view

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.nikolai.ihavepaws.android.R
import com.nikolai.ihavepaws.android.commonComposables.topPanel.TopAddPanel
import com.nikolai.ihavepaws.android.commonComposables.topPanel.TopTitlePanel
import com.nikolai.ihavepaws.android.commonComposables.topPanel.TopTitleWithArrowPanel
import com.nikolai.ihavepaws.android.features.groupsScreen.view.GroupRow
import com.nikolai.ihavepaws.android.model.consts.*
import com.nikolai.ihavepaws.android.model.typealiases.VoidCallback

@Composable
fun GroupScreenView(
    backButtonTapCallback: VoidCallback,
    showAddGroupItemCallback: VoidCallback
) {
    BoxWithConstraints(

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
                title = stringResource(id = R.string.groups_title_text),
                backTapCallback = backButtonTapCallback
            )
            val columnState = rememberLazyListState()
//            LazyColumn(
//                modifier = Modifier.fillMaxSize(),
//                state = columnState,
//                horizontalAlignment = Alignment.CenterHorizontally,
//                verticalArrangement = Arrangement.spacedBy(maxViewHeight.times(groupVertSpacing))
//            ) {
//                items(
//                    items = groupsList.value, itemContent = { item ->
//                        GroupRow(
//                            modifier = Modifier
//                                .width(maxViewWidth.times(groupWidthCoff))
//                                .height(maxViewHeight.times(groupHeightCoff))
//                                .clip(RoundedCornerShape(maxViewHeight.times(groupCornerRadius)))
//                                .clickable { openGroupCallback(item) },
//                            title = item.name,
//                            crossTapCallback = { deleteGroupCallback(item) }
//                        )
//                    })
//            }
        }
    }
}

@Preview
@Composable
fun GroupScreenViewPreview() {
    Surface{
        GroupScreenView(
            showAddGroupItemCallback = { },
            backButtonTapCallback = { }
        )
    }
}