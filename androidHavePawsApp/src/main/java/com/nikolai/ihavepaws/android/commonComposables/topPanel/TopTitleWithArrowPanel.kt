package com.nikolai.ihavepaws.android.commonComposables.topPanel

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Surface
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.nikolai.ihavepaws.android.R
import com.nikolai.ihavepaws.android.model.style.lightBluePrimaryColor
import com.nikolai.ihavepaws.android.model.typealiases.VoidCallback

@Composable
fun TopTitleWithArrowPanel(
    modifier: Modifier = Modifier,
    title: String,
    backTapCallback: VoidCallback
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .height(intrinsicSize = IntrinsicSize.Min),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Start
    ) {
        Image(
            painter = rememberVectorPainter(image = Icons.Filled.ArrowBack),
            contentDescription = stringResource(id = R.string.back_button_description),
            contentScale = ContentScale.FillHeight,
            colorFilter = ColorFilter.tint(lightBluePrimaryColor),
            modifier = Modifier
                .fillMaxHeight(1.0f)
                .clickable { backTapCallback() }
        )
        TopTitlePanel(title = title)
    }
}

@Preview
@Composable
fun TopTitleWithArrowPanelPreview() {
    Surface {
        TopTitleWithArrowPanel(
            title = "Что надеть?",
            backTapCallback = { }
        )
    }
}