package com.nikolai.ihavepaws.android.model.style

import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.nikolai.ihavepaws.style.blueLightPrimary
import com.nikolai.ihavepaws.style.tabTitleTextSize

val topTitleSize = tabTitleTextSize.sp

val smallSize = 13.sp
val mediumSize = 17.sp
val largeSize = 24.sp

val menuTitleFontSize = largeSize
val menuSubTitleFontSize = smallSize
val appButtonFontSize = smallSize
val groupNameFontSize = largeSize

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

val groupNameTextStyle = TextStyle(
    color = lightBluePrimaryColor,
    fontSize = groupNameFontSize,
    fontWeight = FontWeight.SemiBold,
    fontFamily = montserratFont
)