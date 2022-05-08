package com.nikolai.ihavepaws.android.features.groupScreen.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.material.Card
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.nikolai.ihavepaws.android.R
import com.nikolai.ihavepaws.android.commonComposables.buttons.SimpleAppButton
import com.nikolai.ihavepaws.android.model.style.menuTitleTextStyle
import com.nikolai.ihavepaws.android.model.style.rootBackgroundColor
import com.nikolai.ihavepaws.android.model.typealiases.VoidCallback

@Composable
fun RandomItemView(
    modifier: Modifier = Modifier,
    text: String,
    onCloseRequest: VoidCallback
) {
    Dialog(onDismissRequest = onCloseRequest) {
        Card(
            modifier = modifier,
            shape = RoundedCornerShape(10.dp)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(vertical = 16.dp)
                    .background(rootBackgroundColor),
                verticalArrangement = Arrangement.SpaceEvenly,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    stringResource(id = R.string.random_item_title_text),
                    style = menuTitleTextStyle
                )
                Text(
                    text,
                    style = menuTitleTextStyle
                )
                SimpleAppButton(
                    title = stringResource(id = R.string.ok_button_title),
                    enabled = true,
                    onClick = onCloseRequest
                )
            }
        }
    }
}

@Preview
@Composable
fun RandomItemViewPreview() {
    Surface {
        RandomItemView(
            modifier = Modifier.width(480.dp).height(640.dp),
            text = "Рубашка",
            onCloseRequest = { }
        )
    }
}
