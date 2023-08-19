package com.nikolai.ihavepaws.navigation.model

class ScreenStack {
    private val stack: MutableList<Screen> = mutableListOf()

    fun top(): Screen? = when (stack.isEmpty()) {
        true -> null
        false -> stack.last()
    }

    fun push(newScreen: Screen) {
        stack.add(newScreen)
    }

    fun popToPrevious() {
        if (stack.isNotEmpty()) {
            stack.removeLast()
        }
    }

    fun popToRoot() {
        if (stack.isNotEmpty()) {
            stack.clear()
        }
    }
}