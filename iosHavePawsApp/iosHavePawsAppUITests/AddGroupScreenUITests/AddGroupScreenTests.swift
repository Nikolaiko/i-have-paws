import XCTest

class AddGroupScreenTests: XCTestCase {

    private let wrongTestName = String.init(repeating: "r", count: minEntityNameLength - 1)
    private let correntTestName = String.init(repeating: "r", count: minEntityNameLength)

    override func setUpWithError() throws {
        continueAfterFailure = false
    }

    override func tearDownWithError() throws {}

    func testAddButtonDisabledAtStart() throws {
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

        XCTAssertFalse(addButton.isEnabled)
    }

    func testNotEnoughEntityLength() throws {
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

        entityTextField.tap()
        entityTextField.typeText(wrongTestName)

        XCTAssertFalse(addButton.isEnabled)
    }

    func testEnoughEntityLength() throws {
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

        entityTextField.tap()
        entityTextField.typeText(correntTestName)

        XCTAssertTrue(addButton.isEnabled)
    }

    func testAbilityToCloseWithSwipe() throws {
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

        app.windows.children(matching: .other).element.children(matching: .other).element(boundBy: 1).swipeDown(velocity: XCUIGestureVelocity.fast)


        XCTAssertFalse(entityTextField.exists)
        XCTAssertFalse(addButton.exists)
    }
}
