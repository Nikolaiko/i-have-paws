package com.nikolai.ihavepaws.android.features.groupScreen.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import com.nikolai.ihavepaws.android.R
import com.nikolai.ihavepaws.android.model.consts.groupRowHorizontalPadding
import com.nikolai.ihavepaws.android.model.consts.groupRowVerticalPadding
import com.nikolai.ihavepaws.android.model.style.groupItemNameTextStyle
import com.nikolai.ihavepaws.android.model.style.groupNameTextStyle
import com.nikolai.ihavepaws.android.model.style.lightBlue30
import com.nikolai.ihavepaws.android.model.typealiases.VoidCallback

@Composable
fun GroupItemRow(
    modifier: Modifier = Modifier,
    title: String,
    isActive: Boolean,
    crossTapCallback: VoidCallback,
    toggleState: VoidCallback
) {
    val resourceId = when(isActive) {
        true -> R.drawable.svg_selected
        false -> R.drawable.svg_unselected
    }

    BoxWithConstraints(
        modifier = modifier
    ) {
        val viewMaxWidth = maxWidth
        val viewMaxHeight = maxHeight
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = viewMaxWidth.times(groupRowHorizontalPadding))
                .padding(vertical = viewMaxHeight.times(groupRowVerticalPadding)),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Image(
                imageVector = ImageVector.vectorResource(id = resourceId),
                contentDescription = stringResource(id = R.string.toggle_status_button_description),
                contentScale = ContentScale.FillHeight,
                modifier = Modifier
                    .height(viewMaxHeight.times(0.3f))
                    .padding(end = viewMaxWidth.times(0.03f))
                    .clickable { toggleState() }
            )
            Text(
                title,
                style = groupItemNameTextStyle,
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
fun GroupItemRowPreview() {
    Surface {
        GroupItemRow(
            title = "Title",
            isActive = true,
            crossTapCallback = {},
            toggleState = { }
        )
    }
}