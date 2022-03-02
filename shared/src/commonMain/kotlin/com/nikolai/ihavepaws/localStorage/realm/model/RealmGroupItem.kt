package com.nikolai.ihavepaws.localStorage.realm.model

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

class RealmGroupItem : RealmObject {
    @PrimaryKey
    var id: String = ""
    var title: String = ""
}
