import XCTest

class InitialStateUITest: XCTestCase {

    override func setUpWithError() throws {
        continueAfterFailure = false
    }

    func testCheckItemsExistance() throws {
        let app = XCUIApplication()
        app.launch()

        let title = app.staticTexts[screenTitleLabel]
        let plusButton = app.images[addEntityButtonLabel]

        let titleExist = title.waitForExistence(timeout: waitForExistanceDelay)
        let buttonExist = plusButton.waitForExistence(timeout: waitForExistanceDelay)

        XCTAssertTrue(titleExist, "No title button on initial screen")
        XCTAssertTrue(buttonExist, "No plus button on initial screen")
    }

    func testNoOtherScreenAtStart() throws {
        let app = XCUIApplication()
        app.launch()

        let addGroupView = app.otherElements[addGroupViewLabel]
        let exist = addGroupView.waitForExistence(timeout: waitForExistanceDelay)

        XCTAssertFalse(exist)
    }

    func testShowAddScreenOnAddTap() throws {
        let app = XCUIApplication()
        app.launch()

        let plusButton = app.images[addEntityButtonLabel]
        let buttonExist = plusButton.waitForExistence(timeout: waitForExistanceDelay)
        XCTAssertTrue(buttonExist, "No plus button on initial screen")

        XCUIApplication().images[addEntityButtonLabel].tap()

        let entityTextField = app.textFields["Имя группы"]
        let addButton = app.buttons[addGroupViewLabel]

        let entityTitleExist = entityTextField.waitForExistence(timeout: waitForExistanceDelay)
        let addButtonExist = addButton.waitForExistence(timeout: waitForExistanceDelay)

        XCTAssertTrue(entityTitleExist)
        XCTAssertTrue(addButtonExist)
    }
}
