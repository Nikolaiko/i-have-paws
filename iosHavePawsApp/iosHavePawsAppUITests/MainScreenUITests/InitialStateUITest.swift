import XCTest

class InitialStateUITest: XCTestCase {

    override func setUpWithError() throws {
        continueAfterFailure = false
    }

    func testCheckItemsExistance() throws {
        let app = XCUIApplication()
        app.launch()

        let title = app.otherElements[screenTitleLabel]
        let plusButton = app.otherElements[addEntityButtonLabel]

        XCTAssertTrue(title.exists, "No title button on initial screen")
        XCTAssertTrue(plusButton.exists, "No plus button on initial screen")
    }

    func testNoOtherScreenAtStart() throws {
        let app = XCUIApplication()
        app.launch()

        let addGroupView = app.otherElements[addGroupViewLabel]
        XCTAssertFalse(addGroupView.exists)
    }

    func testShowAddScreenOnAddTap() throws {
        let app = XCUIApplication()
        app.launch()

        let plusButton = app.images[addEntityButtonLabel]
        XCTAssertTrue(plusButton.exists, "No plus button on initial screen")

        XCUIApplication().images[addEntityButtonLabel].tap()

        let entityTextField = app.textFields["Имя группы"]
        let addButton = app.buttons[addGroupViewLabel]

        XCTAssertTrue(entityTextField.exists)
        XCTAssertTrue(addButton.exists)
    }
}
