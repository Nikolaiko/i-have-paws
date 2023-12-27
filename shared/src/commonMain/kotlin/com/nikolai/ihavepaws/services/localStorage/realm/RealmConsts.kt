package com.nikolai.ihavepaws.services.localStorage.realm

import com.nikolai.ihavepaws.services.localStorage.realm.dbObjects.RandomGroup

const val realmSchemaVersion = 1L

val realmDbSchema = setOf(RandomGroup::class)