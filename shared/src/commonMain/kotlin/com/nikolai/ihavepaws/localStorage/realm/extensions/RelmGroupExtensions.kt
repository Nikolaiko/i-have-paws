package com.nikolai.ihavepaws.localStorage.realm.extensions

import com.nikolai.ihavepaws.localStorage.realm.model.RealmGroup
import com.nikolai.ihavepaws.localStorage.realm.model.RealmGroupItem
import com.nikolai.ihavepaws.model.Group
import com.nikolai.ihavepaws.model.GroupItem
import io.realm.toRealmList

fun RealmGroupItem.toGroupItem(): GroupItem = GroupItem(id = id, title = title)

fun RealmGroup.toGroup(): Group {
    return Group(
        id = id,
        name = name,
        items = items.map { it.toGroupItem() }
    )
}


fun GroupItem.toRealmItem(): RealmGroupItem {
    val groupItem = RealmGroupItem()
    groupItem.id = id
    groupItem.title = title
    return groupItem
}

fun Group.toRealmItem(): RealmGroup {
    val group = RealmGroup()
    group.id = id
    group.name = name
    group.items = items.map { it.toRealmItem() }.toRealmList()
    return group
}