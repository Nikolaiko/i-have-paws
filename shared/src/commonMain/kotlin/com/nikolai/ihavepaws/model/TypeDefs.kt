package com.nikolai.ihavepaws.model

import com.nikolai.ihavepaws.groupScreen.contract.GroupScreen
import com.nikolai.ihavepaws.groupsScreen.contract.GroupsScreen

typealias VoidCallback = () -> Unit
typealias DataCallback<T> = (T) -> Unit

typealias GroupScreenCallback = (GroupScreen.State) -> Unit
typealias GroupsScreenCallback = (GroupsScreen.State) -> Unit
typealias MessageCallback = (StateMessage) -> Unit