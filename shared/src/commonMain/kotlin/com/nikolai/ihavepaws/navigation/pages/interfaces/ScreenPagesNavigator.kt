package com.nikolai.ihavepaws.navigation.pages.interfaces

import com.arkivanov.decompose.ExperimentalDecomposeApi
import com.arkivanov.decompose.router.pages.ChildPages
import com.arkivanov.decompose.value.Value

@OptIn(ExperimentalDecomposeApi::class)
interface ScreenPagesNavigator {
    val pages: Value<ChildPages<*, ScreenPage>>
    fun selectPage(index: Int)
}