package com.nikolai.ihavepaws.android.features.addGroupItemScreen.view

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.nikolai.ihavepaws.android.R
import com.nikolai.ihavepaws.android.commonComposables.buttons.SimpleAppButton
import com.nikolai.ihavepaws.android.features.addGroupItemScreen.viewModel.AddGroupItemViewModel
import com.nikolai.ihavepaws.android.features.addGroupScreen.viewModel.AddGroupViewModel
import com.nikolai.ihavepaws.android.model.ViewModelMessage
import com.nikolai.ihavepaws.android.model.consts.errorEffectName
import com.nikolai.ihavepaws.android.model.consts.initialEffectName
import com.nikolai.ihavepaws.android.model.style.lightBluePrimaryColor
import com.nikolai.ihavepaws.android.model.style.menuSubTitleTextStyle
import com.nikolai.ihavepaws.android.model.style.menuTitleTextStyle
import com.nikolai.ihavepaws.android.model.style.rootBackgroundColor
import com.nikolai.ihavepaws.android.model.typealiases.VoidCallback
import org.koin.androidx.compose.getViewModel

@Composable
fun AddGroupItemScreen(
    modifier: Modifier = Modifier,
    selectedGroupId: String,
    onSuccess: VoidCallback,
    onCloseRequest: VoidCallback
) {
    val addViewModel: AddGroupItemViewModel = getViewModel()
    val groupName = addViewModel.state.entityName.observeAsState("")
    val addEnabled = addViewModel.state.addButtonEnabled.observeAsState(false)

    LaunchedEffect(initialEffectName) {
        addViewModel.resetViewModel()
        addViewModel.initGroup(selectedGroupId)
    }

    val context = LocalContext.current.applicationContext
    LaunchedEffect(errorEffectName) {
        addViewModel.state.messages.collect {
            when(it) {
                is ViewModelMessage.Success -> onSuccess()
                else -> {
                    Toast.makeText(context, it.message, Toast.LENGTH_LONG).show()
                }
            }
        }
    }

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
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    stringResource(id = R.string.add_group_item_title_text),
                    style = menuTitleTextStyle
                )
                Text(
                    stringResource(id = R.string.add_group_subtitle_text),
                    style = menuSubTitleTextStyle
                )
                Spacer(modifier = Modifier.weight(weight = 1.0f, fill = true))
                TextField(
                    value = groupName.value,
                    onValueChange = addViewModel::onGroupNameChange,
                    colors = TextFieldDefaults.textFieldColors(
                        backgroundColor = rootBackgroundColor,
                        focusedIndicatorColor = lightBluePrimaryColor,
                        unfocusedIndicatorColor = lightBluePrimaryColor,
                        disabledIndicatorColor = lightBluePrimaryColor
                    ),
                    textStyle = menuSubTitleTextStyle
                )
                Spacer(modifier = Modifier.weight(weight = 1.0f, fill = true))
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceAround
                ) {
                    SimpleAppButton(
                        title = stringResource(id = R.string.cancel_button_title),
                        enabled = true,
                        onClick = onCloseRequest
                    )
                    SimpleAppButton(
                        title = stringResource(id = R.string.add_button_title),
                        enabled = addEnabled.value,
                        onClick = addViewModel::addGroup
                    )
                }
            }
        }
    }
}

@Preview
@Composable
fun AddGroupItemScreenPreview() {
    Surface {
        AddGroupItemScreen(
            selectedGroupId = "",
            onSuccess = { },
            onCloseRequest =  { }
        )
    }
}