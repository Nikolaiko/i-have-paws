package com.nikolai.ihavepaws.android.commonComposables.buttons

import com.nikolai.ihavepaws.android.model.typealiases.VoidCallback

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.nikolai.ihavepaws.android.model.style.appButtonTextStyle
import com.nikolai.ihavepaws.android.model.style.disabledButtonBackground
import com.nikolai.ihavepaws.android.model.style.lightBluePrimaryColor

@Composable
fun SimpleAppButton(
    modifier: Modifier = Modifier,
    title: String = "",
    enabled: Boolean = true,
    onClick: VoidCallback = {}
) {
    Button(
        enabled = enabled,
        onClick = onClick,
        shape = RoundedCornerShape(6.dp),
        colors = ButtonDefaults.buttonColors(
            disabledBackgroundColor = disabledButtonBackground,
            backgroundColor = lightBluePrimaryColor
        ),
        modifier = modifier
    ) {
        Text(
            text = title,
            style = appButtonTextStyle,
            modifier = Modifier
                .padding(vertical = 6.dp)
                .padding(horizontal = 20.dp)
        )
    }
}


@Preview
@Composable
fun SimpleAppButtonPreview() {
    Surface {
        SimpleAppButton(
            title = "Use the discount ->",
            enabled = true,
            onClick = {}
        )
    }
}