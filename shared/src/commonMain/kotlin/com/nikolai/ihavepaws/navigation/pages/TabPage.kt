package com.nikolai.ihavepaws.navigation.pages

import com.arkivanov.decompose.ComponentContext
import com.nikolai.ihavepaws.navigation.pages.interfaces.ScreenPage

class TabPage(
    componentContext: ComponentContext,
    override val data: String,
): ScreenPage, ComponentContext by componentContext