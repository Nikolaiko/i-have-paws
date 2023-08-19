package com.nikolai.ihavepaws.commonViews

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun BottomSheetView(
    visible: Boolean,
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit
) {
    AnimatedVisibility(
        visible = visible,
        enter = slideInVertically(
            animationSpec = tween(durationMillis = 300),
            initialOffsetY = { it }
        ),
        exit = slideOutVertically(
            animationSpec = tween(durationMillis = 300),
            targetOffsetY = { it }
        )
    ) {
        content()
    }
}