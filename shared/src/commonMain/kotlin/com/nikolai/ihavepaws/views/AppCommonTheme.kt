package com.nikolai.ihavepaws.views

import androidx.compose.runtime.Composable

@Composable
expect fun AppCommonTheme(
    darkTheme: Boolean,
    dynamicColor: Boolean,
    content: @Composable () -> Unit
)