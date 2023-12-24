package com.nikolai.ihavepaws.previews.addGroupView

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.nikolai.ihavepaws.addGroupScreen.view.AddGroupScreen
import com.nikolai.ihavepaws.groupsScreen.contract.GroupsScreen
import com.nikolai.ihavepaws.groupsScreen.view.GroupsView
import com.nikolai.ihavepaws.style.multiplatform.appTypography
import com.nikolai.ihavepaws.style.multiplatform.darkScheme
import com.nikolai.ihavepaws.style.multiplatform.lightScheme

@Preview
@Composable
fun AddGroupViewLightPreview() {
    MaterialTheme(
        colorScheme = lightScheme,
        typography = appTypography
    ) {
        AddGroupScreen()
    }
}

@Preview
@Composable
fun AddGroupViewDarkPreview() {
    MaterialTheme(
        colorScheme = darkScheme,
        typography = appTypography
    ) {
        AddGroupScreen()
    }
}