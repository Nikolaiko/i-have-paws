package com.nikolai.ihavepaws.android

import androidx.compose.material.Surface
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.lifecycle.MutableLiveData
import com.nikolai.ihavepaws.android.features.groupsScreen.model.GroupsViewState
import com.nikolai.ihavepaws.android.features.groupsScreen.view.GroupsScreenView
import com.nikolai.ihavepaws.android.model.ViewModelMessage
import com.nikolai.ihavepaws.model.Group
import kotlinx.coroutines.flow.MutableSharedFlow
import org.junit.Rule
import org.junit.Test

class MainScreenTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    private val groupsData = MutableLiveData<List<Group>>(emptyList())
    private val showDialog = MutableLiveData(false)
    private val messages = MutableSharedFlow<ViewModelMessage>()

    private val uiState = GroupsViewState(
        groups = groupsData,
        showAddGroupScreen = showDialog,
        messages = messages
    )

    @Test
    fun myTest() {
        composeTestRule.setContent {
            Surface {
                GroupsScreenView(
                    uiState,
                    initGroupsCallback = { },
                    showAddGroupCallback = { },
                    hideAddGroupCallback = { },
                    deleteGroupCallback = { },
                    openGroupCallback = { }
                )
            }
        }

        composeTestRule.onNodeWithText("Welcome").assertIsDisplayed()
    }
}