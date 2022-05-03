package com.nikolai.ihavepaws.android

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import com.nikolai.ihavepaws.android.features.groupsScreen.view.GroupsScreenView
import com.nikolai.ihavepaws.android.features.groupsScreen.viewModel.GroupsScreenViewModel
import com.nikolai.ihavepaws.android.navigation.AppScreens
import com.nikolai.ihavepaws.android.model.consts.appMainGraph
import org.koin.androidx.compose.viewModel

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            val navigationController = rememberNavController()

            NavHost(navController = navigationController, startDestination = appMainGraph) {
                navigation(startDestination = AppScreens.MainScreen.route, route = appMainGraph) {
                    composable(route = AppScreens.MainScreen.route) {
                        val groupScreenViewModel: GroupsScreenViewModel by viewModel()
                        GroupsScreenView(
                            state = groupScreenViewModel.state,
                            initGroupsCallback = groupScreenViewModel::initGroupsList,
                            showAddGroupCallback = groupScreenViewModel::showAddGroupScreen,
                            hideAddGroupCallback = groupScreenViewModel::hideAddGroupScreen
                        )
                    }
                }
            }
        }
    }
}
