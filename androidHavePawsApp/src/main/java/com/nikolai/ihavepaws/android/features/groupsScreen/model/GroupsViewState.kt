package com.nikolai.ihavepaws.android.features.groupsScreen.model

import androidx.lifecycle.LiveData
import com.nikolai.ihavepaws.android.model.ViewModelMessage
import com.nikolai.ihavepaws.model.Group
import kotlinx.coroutines.flow.SharedFlow

data class GroupsViewState(
    val groups: LiveData<List<Group>>,
    val showAddGroupScreen: LiveData<Boolean>,
    val messages: SharedFlow<ViewModelMessage>
)
