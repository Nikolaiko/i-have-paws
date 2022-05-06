package com.nikolai.ihavepaws.android.features.addGroupScreen.model

import androidx.lifecycle.LiveData
import com.nikolai.ihavepaws.android.model.ViewModelMessage
import kotlinx.coroutines.flow.SharedFlow

data class AddEntityScreenState(
    val entityName: LiveData<String>,
    val addButtonEnabled: LiveData<Boolean>,
    val messages: SharedFlow<ViewModelMessage>
)
