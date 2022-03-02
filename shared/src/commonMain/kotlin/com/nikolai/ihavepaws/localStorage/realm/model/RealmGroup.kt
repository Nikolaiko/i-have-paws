package com.nikolai.ihavepaws.localStorage.realm.model

import io.realm.RealmList
import io.realm.RealmObject
import io.realm.annotations.PrimaryKey
import io.realm.realmListOf

class RealmGroup : RealmObject {
    @PrimaryKey
    var id: String = ""
    var name: String = ""
    var items: RealmList<RealmGroupItem> = realmListOf()
}
