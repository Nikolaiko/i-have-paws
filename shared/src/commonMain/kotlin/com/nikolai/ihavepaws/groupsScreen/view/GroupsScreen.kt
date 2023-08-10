package com.nikolai.ihavepaws.groupsScreen.view

import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.nikolai.ihavepaws.groupsScreen.view.subviews.TopAddButton
import com.nikolai.ihavepaws.groupsScreen.view.subviews.TopTitleLabel
import com.nikolai.ihavepaws.model.consts.groupsScreenTitle
import com.nikolai.ihavepaws.model.consts.topAddHeightCoff

@Composable
fun GroupsScreen() {
    BoxWithConstraints {
        val screenHeight = maxHeight

        Column(
            modifier = Modifier
                .fillMaxSize()
        ) {
            TopAddButton(
                modifier = Modifier
                    .height(screenHeight.times(topAddHeightCoff))
            )
            TopTitleLabel(titleText = groupsScreenTitle)
        }
    }

}
