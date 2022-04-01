package com.nikolai.ihavepaws.model.base

import com.nikolai.ihavepaws.model.StateMessage
import kotlinx.coroutines.flow.SharedFlow

interface BaseReducer {
    val messages: SharedFlow<StateMessage>
}