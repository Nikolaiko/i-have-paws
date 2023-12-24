package com.nikolai.ihavepaws.previews

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.nikolai.ihavepaws.groupsScreen.contract.GroupsScreen
import com.nikolai.ihavepaws.groupsScreen.view.GroupsView
import com.nikolai.ihavepaws.style.multiplatform.appTypography
import com.nikolai.ihavepaws.style.multiplatform.darkScheme
import com.nikolai.ihavepaws.style.multiplatform.lightScheme

@Preview
@Composable
fun GroupsViewLightPreview() {
    MaterialTheme(
        colorScheme = lightScheme,
        typography = appTypography
    ) {
        GroupsView(
            state = GroupsScreen.State(),
            onAddGroup = { }
        )
    }
}

@Preview
@Composable
fun GroupsViewDarkPreview() {
    MaterialTheme(
        colorScheme = darkScheme,
        typography = appTypography
    ) {
        GroupsView(
            state = GroupsScreen.State(),
            onAddGroup = { }
        )
    }
}