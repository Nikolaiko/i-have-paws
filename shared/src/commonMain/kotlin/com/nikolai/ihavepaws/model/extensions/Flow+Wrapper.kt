package com.nikolai.ihavepaws.model.extensions

import com.nikolai.ihavepaws.model.flowsProxy.AnyFlow
import kotlinx.coroutines.flow.Flow

fun <T> Flow<T>.wrapToAny(): AnyFlow<T> = AnyFlow(this)