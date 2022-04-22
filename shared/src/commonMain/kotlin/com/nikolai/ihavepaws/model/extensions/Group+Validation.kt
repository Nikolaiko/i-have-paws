package com.nikolai.ihavepaws.model.extensions

import com.nikolai.ihavepaws.model.Group

fun Group.isEnabledForRandom(): Boolean {
    val activeItem = items.firstOrNull { groupItem -> groupItem.active }
    return activeItem != null
}