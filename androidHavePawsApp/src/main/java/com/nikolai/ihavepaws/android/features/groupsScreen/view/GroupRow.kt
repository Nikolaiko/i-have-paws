package com.nikolai.ihavepaws.android.features.groupsScreen.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import com.nikolai.ihavepaws.android.R
import com.nikolai.ihavepaws.android.model.consts.groupRowHorizontalPadding
import com.nikolai.ihavepaws.android.model.consts.groupRowVerticalPadding
import com.nikolai.ihavepaws.android.model.style.groupNameTextStyle
import com.nikolai.ihavepaws.android.model.style.lightBlue30
import com.nikolai.ihavepaws.android.model.typealiases.VoidCallback

@Composable
fun GroupRow(
    modifier: Modifier = Modifier,
    title: String,
    crossTapCallback: VoidCallback
) {
    BoxWithConstraints(
        modifier = modifier
            .background(lightBlue30)
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = maxWidth.times(groupRowHorizontalPadding))
                .padding(vertical = maxHeight.times(groupRowVerticalPadding)),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Text(
                title,
                style = groupNameTextStyle,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier.weight(1.0f)
            )
            Image(
                painterResource(id = R.drawable.png_delete_button),
                contentDescription = stringResource(id = R.string.delete_button_description),
                contentScale = ContentScale.FillHeight,
                modifier = Modifier
                    .fillMaxHeight(1.0f)
                    .clickable { crossTapCallback() }
            )
        }
    }
}

@Preview
@Composable
fun GroupRowPreview() {
    Surface {
        GroupRow(
            title = "Skuratov",
            crossTapCallback = { }
        )
    }
}
