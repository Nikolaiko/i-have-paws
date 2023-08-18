package com.nikolai.ihavepaws.localStorage.realm.dbObjects

import io.realm.kotlin.types.RealmObject
import io.realm.kotlin.types.annotations.PrimaryKey
import org.mongodb.kbson.ObjectId

class RandomGroup(): RealmObject {
    @PrimaryKey
    var id: String = ObjectId().toHexString()
    var name: String = ""
}