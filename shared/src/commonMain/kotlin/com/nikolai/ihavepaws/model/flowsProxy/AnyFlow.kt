package com.nikolai.ihavepaws.model.flowsProxy

import com.nikolai.ihavepaws.model.FlowCompletion
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.Flow

class AnyFlow<T>(
    baseFlow: Flow<T>
) : Flow<T> by baseFlow {
    fun collect(onEach: (T) -> Unit, onComplete: FlowCompletion): Cancellable {
        val scope = CoroutineScope(SupervisorJob() + Dispatchers.Main)

        scope.launch {
            try {
                collect {
                    onEach(it)
                }
                onComplete(null)
            } catch (e: Throwable) {
                onComplete(e)
            }
        }

        return object : Cancellable {
            override fun cancel() {
                scope.cancel()
            }

        }
    }
}