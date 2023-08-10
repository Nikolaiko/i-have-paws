package com.nikolai.ihavepaws.views

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import com.nikolai.ihavepaws.style.multiplatform.appTypography
import com.nikolai.ihavepaws.style.multiplatform.darkScheme
import com.nikolai.ihavepaws.style.multiplatform.lightScheme

@Composable
actual fun AppCommonTheme(
    darkTheme: Boolean,
    dynamicColor: Boolean,
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colorScheme = when(darkTheme) {
            true -> darkScheme
            false -> lightScheme
        },
        typography = appTypography,
        content = content
    )
}
