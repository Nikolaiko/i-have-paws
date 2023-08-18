package realmTests

import app.cash.turbine.turbineScope
import com.nikolai.ihavepaws.localStorage.realm.RealmStorage
import com.nikolai.ihavepaws.localStorage.realm.realmDbSchema
import com.nikolai.ihavepaws.localStorage.realm.realmSchemaVersion
import com.nikolai.ihavepaws.model.Group
import io.realm.kotlin.RealmConfiguration
import kotlinx.coroutines.test.runTest
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotNull
import kotlin.test.assertTrue

class RealmStorageTests {
    private val config = RealmConfiguration
        .Builder(schema = realmDbSchema)
        .schemaVersion(schemaVersion = realmSchemaVersion)
        .inMemory()
        .build()
    private val realmInstance = RealmStorage(config)

    @Test
    fun addGroupSuspendedTest() = runTest {
        val groupsFlow = realmInstance.getGroupsFlow()
        turbineScope {
            val groupsTurbine = groupsFlow.testIn(this)
            assertEquals(emptyList(), groupsTurbine.awaitItem())

            realmInstance.addRandomGroup(addGroupTest)

            val groups = groupsTurbine.awaitItem()
            assertTrue(groups.isNotEmpty())
            assertTrue(groups.contains(addGroupTest))
            groupsTurbine.cancelAndIgnoreRemainingEvents()
        }
    }

    @Test
    fun addGroupBlockingTest() {
        val addedGroupResult: Result<Group> = realmInstance.addNewGroup(addGroupTest)
        assertTrue(addedGroupResult.isSuccess)
        assertNotNull(addedGroupResult.getOrNull())
        assertEquals(addGroupTest, addedGroupResult.getOrNull()!!)

        val groups = realmInstance.getAllGroups()
        assertTrue(groups.isNotEmpty())
    }
}