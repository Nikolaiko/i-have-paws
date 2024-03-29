package com.nikolai.ihavepaws.android

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.isSystemInDarkTheme
import com.nikolai.ihavepaws.appScreens.views.AppMainView
import com.nikolai.ihavepaws.android.navigation.AppNavigator

class MainActivity : AppCompatActivity() {

    private lateinit var navigation: AppNavigator

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            AppMainView()
//            val navigationController = rememberNavController()
//
//            navigation = get()
//            navigation.setNavigator(navigationController)
//
//            NavHost(navController = navigationController, startDestination = appMainGraph) {
//                navigation(startDestination = AppScreens.MainScreen.route, route = appMainGraph) {
//                    composable(route = AppScreens.MainScreen.route) {
//                        val groupScreenViewModel: GroupsScreenViewModel by viewModel()
//                        GroupsScreenView(
//                            state = groupScreenViewModel.state,
//                            initGroupsCallback = groupScreenViewModel::initGroupsList,
//                            showAddGroupCallback = groupScreenViewModel::showAddGroupScreen,
//                            hideAddGroupCallback = groupScreenViewModel::hideAddGroupScreen,
//                            deleteGroupCallback = groupScreenViewModel::deleteGroup,
//                            openGroupCallback = groupScreenViewModel::openGroup
//                        )
//                    }
//                    composable(route = AppScreens.GroupScreen.route) {
//                        val groupName = it.arguments?.getString(groupIdParameter) ?: ""
//                        val groupViewModel: GroupScreenViewModel by viewModel()
//
//                        GroupScreenView(
//                            state = groupViewModel.state,
//                            groupName = groupName,
//                            initGroupCallback = groupViewModel::initWithGroup,
//                            backButtonTapCallback = groupViewModel::backButtonCallback,
//                            showAddGroupItemCallback = groupViewModel::showAddGroupItemScreen,
//                            hideAddGroupItemCallback = groupViewModel::hideAddGroupItemScreen,
//                            deleteGroupItemCallback = groupViewModel::deleteGroupItem,
//                            toggleStateCallback = groupViewModel::toggleGroupItemStatus,
//                            selectRandomElementCallback = groupViewModel::selectRandomElement,
//                            hideRandomItemMenuCallback = groupViewModel::hideRandomItemMenu
//                        )
//                    }
//                }
//            }
        }
    }
}
