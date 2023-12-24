package com.nikolai.ihavepaws.addGroupScreen.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.nikolai.ihavepaws.model.consts.addGroupScreenSubTitle
import com.nikolai.ihavepaws.model.consts.addGroupScreenTitle

@Composable
fun AddGroupScreen(

) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.surface)
    ) {
        Text(
            addGroupScreenTitle,
            color = MaterialTheme.colorScheme.primary,
            style = MaterialTheme.typography.titleMedium
        )
        Text(
            addGroupScreenSubTitle,
            color = MaterialTheme.colorScheme.primary,
            style = MaterialTheme.typography.bodyMedium
        )
//        Spacer(modifier = Modifier.weight(weight = 1.0f, fill = true))
//        TextField(
//            value = groupName.value,
//            onValueChange = addViewModel::onGroupNameChange,
//            colors = TextFieldDefaults.textFieldColors(
//                backgroundColor = rootBackgroundColor,
//                focusedIndicatorColor = lightBluePrimaryColor,
//                unfocusedIndicatorColor = lightBluePrimaryColor,
//                disabledIndicatorColor = lightBluePrimaryColor
//            ),
//            textStyle = menuSubTitleTextStyle
//        )
//        Spacer(modifier = Modifier.weight(weight = 1.0f, fill = true))
//        Row(
//            modifier = Modifier
//                .fillMaxWidth(),
//            horizontalArrangement = Arrangement.SpaceAround
//        ) {
//            SimpleAppButton(
//                title = stringResource(id = R.string.cancel_button_title),
//                enabled = true,
//                onClick = onCloseRequest
//            )
//            SimpleAppButton(
//                title = stringResource(id = R.string.add_button_title),
//                enabled = addEnabled.value,
//                onClick = addViewModel::addGroup
//            )
//        }
//    }
    }
}