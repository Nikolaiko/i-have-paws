package com.nikolai.ihavepaws.model.base

import com.nikolai.ihavepaws.model.StateMessage
import com.nikolai.ihavepaws.model.flowsProxy.AnyFlow
import kotlinx.coroutines.flow.SharedFlow

interface BaseReducer {
    val messages: AnyFlow<StateMessage>
}