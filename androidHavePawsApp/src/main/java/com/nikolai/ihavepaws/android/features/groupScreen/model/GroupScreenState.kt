package com.nikolai.ihavepaws.android.features.groupScreen.model

import androidx.lifecycle.LiveData
import com.nikolai.ihavepaws.android.model.ViewModelMessage
import com.nikolai.ihavepaws.model.GroupItem
import kotlinx.coroutines.flow.SharedFlow

data class GroupScreenState(
    val selectedGroupId: LiveData<String>,
    val randomButtonEnabled: LiveData<Boolean>,
    val addItemMenuShow: LiveData<Boolean>,
    val groupItems: LiveData<List<GroupItem>>,
    val messages: SharedFlow<ViewModelMessage>
)
