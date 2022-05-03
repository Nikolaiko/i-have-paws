package com.nikolai.ihavepaws.android.model.style

import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.nikolai.ihavepaws.style.blueLightPrimary
import com.nikolai.ihavepaws.style.tabTitleTextSize

val topTitleSize = tabTitleTextSize.sp
val menuTitleFontSize = 24.sp
val menuSubTitleFontSize = 13.sp
val appButtonFontSize = 13.sp

val titleTextStyle = TextStyle(
    color = topPanelTitleColor,
    fontSize = topTitleSize,
    fontWeight = FontWeight.Bold,
    fontFamily = montserratFont
)

val menuTitleTextStyle = TextStyle(
    color = menuTitleColor,
    fontSize = menuTitleFontSize,
    fontWeight = FontWeight.SemiBold,
    fontFamily = montserratFont
)

val menuSubTitleTextStyle = TextStyle(
    color = menuSubTitleColor,
    fontSize = menuSubTitleFontSize,
    fontWeight = FontWeight.SemiBold,
    fontFamily = montserratFont
)

val appButtonTextStyle = TextStyle(
    color = appButtonTextColor,
    fontSize = appButtonFontSize,
    fontWeight = FontWeight.SemiBold,
    fontFamily = montserratFont
)