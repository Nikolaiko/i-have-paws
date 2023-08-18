package realmTests

import com.nikolai.ihavepaws.model.Group
import org.mongodb.kbson.ObjectId

const val realmTestTag = "TestRealm"

val testGroupId = ObjectId().toHexString()
const val testGroupName = "Skuratov"

val addGroupTest = Group(
    id = testGroupId,
    name = testGroupName,
    emptyList()
)