package com.nikolai.ihavepaws.android.commonComposables.topPanel

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.nikolai.ihavepaws.android.model.consts.topTitleBottomPadding
import com.nikolai.ihavepaws.android.model.consts.topTitleLeadingPadding
import com.nikolai.ihavepaws.android.model.style.titleTextStyle

@Composable
fun TopTitlePanel(
    modifier: Modifier = Modifier,
    title: String
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(bottom = topTitleBottomPadding.dp),
        horizontalArrangement = Arrangement.Start
    ) {
        Text(
            text = title,
            style = titleTextStyle,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier
                .padding(start = topTitleLeadingPadding.dp)
        )
    }
}

@Preview
@Composable
fun TopTitlePanelPreview() {
    Surface {
        TopTitlePanel(title = "Группы")
    }
}