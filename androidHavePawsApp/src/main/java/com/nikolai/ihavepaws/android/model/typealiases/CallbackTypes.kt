package com.nikolai.ihavepaws.android.model.typealiases

import com.nikolai.ihavepaws.model.Group
import com.nikolai.ihavepaws.model.GroupItem

typealias VoidCallback = () -> Unit
typealias StringCallback = (String) -> Unit
typealias GroupActionCallback = (Group) -> Unit
typealias GroupItemActionCallback = (GroupItem) -> Unit
