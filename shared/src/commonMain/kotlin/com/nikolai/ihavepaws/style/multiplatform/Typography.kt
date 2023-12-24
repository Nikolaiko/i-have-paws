package com.nikolai.ihavepaws.style.multiplatform

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

const val topTitleSize = 32
const val mediumTitleSize = 24
const val smallTitleSize = 16


val appFontFamily = FontFamily.Default

val appTypography = Typography(
    titleMedium = TextStyle(
        fontSize = topTitleSize.sp,
        fontWeight = FontWeight.Bold,
        fontFamily = appFontFamily
    ),
    bodyMedium = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.SemiBold,
        fontSize = mediumTitleSize.sp,
    ),
    labelMedium = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Medium,
        fontSize = smallTitleSize.sp,
    )
)