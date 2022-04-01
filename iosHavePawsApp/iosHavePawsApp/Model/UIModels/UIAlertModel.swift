import Foundation
import SwiftUI

public struct UIAlertModel {
  public var title: String
  public var message: String
  public var placeholder: String = ""
  public var accept: String = "OK"
  public var cancel: String? = "Cancel"
  public var secondaryActionTitle: String? = nil
  public var keyboardType: UIKeyboardType = .default
  public var action: (String?) -> Void
  public var secondaryAction: (() -> Void)? = nil
}
