package com.nikolai.ihavepaws.groupsScreen.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.nikolai.ihavepaws.addGroupScreen.view.AddGroupScreen
import com.nikolai.ihavepaws.commonViews.BottomSheetView
import com.nikolai.ihavepaws.groupsScreen.contract.GroupsScreen
import com.nikolai.ihavepaws.groupsScreen.view.subviews.TopAddButton
import com.nikolai.ihavepaws.groupsScreen.view.subviews.TopTitleLabel
import com.nikolai.ihavepaws.model.DataCallback
import com.nikolai.ihavepaws.model.consts.groupsScreenTitle
import com.nikolai.ihavepaws.model.consts.topAddHeightCoff

@Composable
fun GroupsView(
    state: GroupsScreen.State,
    onAddGroup: DataCallback<GroupsScreen.Event>
) {
    BoxWithConstraints {
        val screenHeight = maxHeight
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.surface)
        ) {
            TopAddButton(
                modifier = Modifier
                    .height(screenHeight.times(topAddHeightCoff)),
                onTap = { onAddGroup(GroupsScreen.Event.AddNewGroup) }
            )
            TopTitleLabel(titleText = groupsScreenTitle)
        }
        BottomSheetView(
            visible = state.addGroupVisible,
            content = { AddGroupScreen() }
        )
    }
}
