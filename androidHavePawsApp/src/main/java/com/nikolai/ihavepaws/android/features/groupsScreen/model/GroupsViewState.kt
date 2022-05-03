package com.nikolai.ihavepaws.android.features.groupsScreen.model

import androidx.lifecycle.LiveData
import com.nikolai.ihavepaws.model.Group

data class GroupsViewState(
    val groups: LiveData<List<Group>>,
    val showAddGroupScreen: LiveData<Boolean>
)
