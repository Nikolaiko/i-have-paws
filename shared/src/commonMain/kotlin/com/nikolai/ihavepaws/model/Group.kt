package com.nikolai.ihavepaws.model

data class Group(
    val id: String,
    val name: String,
    val items: List<GroupItem>
)
