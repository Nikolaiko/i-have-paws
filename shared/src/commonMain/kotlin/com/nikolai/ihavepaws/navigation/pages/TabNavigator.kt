package com.nikolai.ihavepaws.navigation.pages

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.ExperimentalDecomposeApi
import com.arkivanov.decompose.router.pages.ChildPages
import com.arkivanov.decompose.router.pages.Pages
import com.arkivanov.decompose.router.pages.PagesNavigation
import com.arkivanov.decompose.router.pages.childPages
import com.arkivanov.decompose.router.pages.select
import com.arkivanov.decompose.value.Value
import com.nikolai.ihavepaws.navigation.pages.interfaces.ScreenPagesNavigator
import com.nikolai.ihavepaws.navigation.pages.interfaces.TabPageConfig

@OptIn(ExperimentalDecomposeApi::class)
class TabNavigator(
    componentContext: ComponentContext,
): ScreenPagesNavigator, ComponentContext by componentContext {

    private val navigation = PagesNavigation<TabPageConfig>()

    override val pages: Value<ChildPages<*, TabPage>> =
        childPages(
            source = navigation,
            serializer = TabPageConfig.serializer(),
            initialPages = {
                Pages(
                    items = List(3) { index -> TabPageConfig(data = "Item $index") },
                    selectedIndex = 0,
                )
            },
        ) { config, childComponentContext ->
            TabPage(
                componentContext = childComponentContext,
                data = config.data,
            )
        }

    override fun selectPage(index: Int) {
        navigation.select(index = index)
    }
}