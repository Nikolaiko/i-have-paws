package com.nikolai.ihavepaws.android.commonComposables.topPanel

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.material.Surface
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
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
import com.nikolai.ihavepaws.android.model.style.topPanelBackgroundColor
import com.nikolai.ihavepaws.android.model.typealiases.VoidCallback
import com.nikolai.ihavepaws.style.topBarAddIconTrailingPadding

@Composable
fun TopAddPanel(
    modifier: Modifier = Modifier,
    tapCallback: VoidCallback = { }
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .background(topPanelBackgroundColor)
            .padding(end = topBarAddIconTrailingPadding.dp),
        horizontalArrangement = Arrangement.End,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = rememberVectorPainter(image = Icons.Filled.Add),
            contentDescription = stringResource(id = R.string.add_button_description),
            contentScale = ContentScale.FillHeight,
            colorFilter = ColorFilter.tint(lightBluePrimaryColor),
            modifier = Modifier
                .fillMaxHeight(1.0f)
                .clickable { tapCallback() }
        )
    }
}


@Preview
@Composable
fun TopAddPanelPreview() {
    Surface {
        TopAddPanel(
            modifier = Modifier.height(100.dp)
        )
    }
}
