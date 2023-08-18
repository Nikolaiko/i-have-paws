package com.nikolai.ihavepaws.di.components

import com.nikolai.ihavepaws.localStorage.ObservableLocalStorage
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class KoinStorage: KoinComponent {
    val observableLocalStorage: ObservableLocalStorage by inject()
}