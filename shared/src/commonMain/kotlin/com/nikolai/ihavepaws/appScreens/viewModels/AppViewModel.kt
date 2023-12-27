package com.nikolai.ihavepaws.appScreens.viewModels

import com.nikolai.ihavepaws.model.consts.sharingCoroutineDelay
import dev.icerock.moko.mvvm.viewmodel.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.stateIn

class AppViewModel: ViewModel() {
    data class State(
        val isDarkMode: Boolean = false,
        val isTrueColor: Boolean = false
    )

    private val _state = MutableStateFlow(State())
    val state = _state
        .asStateFlow()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(sharingCoroutineDelay),
            initialValue = State()
        )
}