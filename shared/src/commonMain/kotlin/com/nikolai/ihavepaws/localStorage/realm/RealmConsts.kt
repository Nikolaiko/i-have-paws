package com.nikolai.ihavepaws.localStorage.realm

import com.nikolai.ihavepaws.localStorage.realm.dbObjects.RandomGroup

const val realmSchemaVersion = 1L

val realmDbSchema = setOf(RandomGroup::class)